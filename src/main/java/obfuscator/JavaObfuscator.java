package obfuscator;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.nio.file.*;
import java.util.Map;
import java.util.Set;

public class JavaObfuscator {

    private final Map<String, String> classMap;
    private final Map<String, String> methodMap;
    private final Map<String, String> varMap;
    private final Set<String> obfSet;

    public JavaObfuscator(Path sourceRoot,
                          Map<String, String> classM,
                          Map<String, String> methodM,
                          Map<String, String> varM,
                          Set<String> obfS) {
        classMap = classM;
        this.methodMap = methodM;
        this.varMap = varM;
        obfSet = obfS;
        initSymbolSolver(sourceRoot);
    }

    private void initSymbolSolver(Path sourceRoot) {
        CombinedTypeSolver solver = new CombinedTypeSolver();
        solver.add(new ReflectionTypeSolver());
        solver.add(new JavaParserTypeSolver(sourceRoot));

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(solver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);
    }

    public void obfuscate(Path inputFile,
                          Path inputRoot,
                          Path outputRoot) throws Exception {

        CompilationUnit cu = StaticJavaParser.parse(inputFile);
        final String[] newFileName = {inputFile.getFileName().toString()};

        cu.accept(new VoidVisitorAdapter<Void>() {

            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                String oldName = n.getNameAsString();
                String newName = classMap.get(oldName);
                if (newName != null) {
                    n.setName(newName);
                    if (n.isPublic()) {
                        newFileName[0] = newName + ".java";
                    }
                }
            }

            @Override
            public void visit(ClassOrInterfaceType n, Void arg) {
                super.visit(n, arg);
                String name = n.getNameAsString();
                if (classMap.containsKey(name)) {
                    n.setName(classMap.get(name));
                }
            }

            @Override
            public void visit(ConstructorDeclaration n, Void arg) {
                super.visit(n, arg);
                String name = n.getNameAsString();
                if (classMap.containsKey(name)) {
                    n.setName(classMap.get(name));
                }
            }

            @Override
            public void visit(MethodDeclaration n, Void arg) {
                super.visit(n, arg);
                if ("main".equals(n.getNameAsString()) || n.isAnnotationPresent("Override")) {
                    return;
                }
                String oldName = n.getNameAsString();
                String newName = methodMap.get(oldName);
                if (newName != null) {
                    n.setName(newName);
                }
            }

            @Override
            public void visit(MethodCallExpr n, Void arg) {
                super.visit(n, arg);
                try {
                    ResolvedMethodDeclaration r = n.resolve();
                    String originalMethod = r.getName();
                    ResolvedTypeDeclaration declaringType = r.declaringType();
                    String originalClass = declaringType.getClassName();

                    if (methodMap.containsKey(originalMethod) && (classMap.containsKey(originalClass)|| classMap.containsValue(originalClass))) {
                        n.setName(methodMap.get(originalMethod));
                    }
                    return;
                } catch (Exception ignored) {
                    if (n.getScope().isEmpty()) {
                        n.findAncestor(ClassOrInterfaceDeclaration.class)
                                .ifPresent(parent -> {
                                    String currentClass = parent.getNameAsString();
                                    String methodName = n.getNameAsString();
                                    if (classMap.containsKey(currentClass) && methodMap.containsKey(methodName)) {
                                        n.setName(methodMap.get(methodName));
                                    }
                                });
                        return;
                    }

                    if (n.getScope().get().isNameExpr()) {
                        NameExpr scope = n.getScope().get().asNameExpr();
                        String scopeName = scope.getNameAsString();
                        String methodName = n.getNameAsString();
                        if (obfSet.contains(scopeName) && methodMap.containsKey(methodName)) {
                            n.setName(methodMap.get(methodName));
                        }
                    }
                }

                // fallback 1: вызов без scope

            }

            @Override
            public void visit(VariableDeclarator n, Void arg) {
                super.visit(n, arg);
                String old = n.getNameAsString();
                if (varMap.containsKey(old)) {
                    n.setName(varMap.get(old));
                    return;
                }
                varMap.computeIfAbsent(old,k -> NameGenerator.generate("v"));
                n.setName(varMap.get(old));
                if(classMap.containsKey(n.getTypeAsString())){
                    obfSet.add(n.getNameAsString());
                    obfSet.add(old);
                }
            }

            @Override
            public void visit(Parameter n, Void arg) {
                super.visit(n, arg);
                String old = n.getNameAsString();
                String newName = varMap.get(old);
                if (newName != null) {
                    n.setName(newName);
                    if(classMap.containsKey(n.getTypeAsString())){
                        obfSet.add(newName);
                        obfSet.add(old);
                    }
                }
            }

            @Override
            public void visit(NameExpr n, Void arg) {
                super.visit(n, arg);
                String name = n.getNameAsString();
                if (varMap.containsKey(name)) {
                    n.setName(varMap.get(name));
                } else if (classMap.containsKey(name)) {
                    n.setName(classMap.get(name));
                }
            }

            @Override
            public void visit(FieldAccessExpr n, Void arg) {
                super.visit(n, arg);
                String name = n.getNameAsString();
                if (varMap.containsKey(name)) {
                    n.setName(varMap.get(name));
                }
            }

            @Override
            public void visit(ImportDeclaration n, Void arg) {
                super.visit(n, arg);
                if (n.isAsterisk()) return;

                String full = n.getNameAsString();
                int dot = full.lastIndexOf('.');
                if (dot == -1) return;

                String cls = full.substring(dot + 1);
                if (classMap.containsKey(cls)) {
                    n.setName(full.substring(0, dot + 1) + classMap.get(cls));
                }
            }

        }, null);

        Path relative = inputRoot.relativize(inputFile);
        Path outDir = outputRoot.resolve(relative).getParent();
        Files.createDirectories(outDir);
        Files.writeString(outDir.resolve(newFileName[0]), cu.toString());
    }
}