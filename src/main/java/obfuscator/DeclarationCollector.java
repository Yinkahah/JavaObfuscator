package obfuscator;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DeclarationCollector {

    private final Map<String, String> classMap = new HashMap<>();
    private final Map<String, String> methodMap = new HashMap<>();
    private final Map<String, String> varMap = new HashMap<>();

    public void collect(Path inputFile) throws Exception {
        CompilationUnit cu = StaticJavaParser.parse(inputFile);
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                String name = n.getNameAsString();
                classMap.putIfAbsent(name, null); // placeholder
                n.getMembers().forEach(member -> {
                    if (member instanceof MethodDeclaration md) {
                        if (!"main".equals(md.getNameAsString()) && !md.isAnnotationPresent("Override")) {
                            methodMap.putIfAbsent(md.getNameAsString(), null);
                        }
                    } else if (member instanceof FieldDeclaration fd) {
                        fd.getVariables().forEach(vd ->
                                varMap.putIfAbsent(vd.getNameAsString(), null));
                    }
                });
            }

            @Override
            public void visit(Parameter n, Void arg) {
                super.visit(n, arg);
                varMap.putIfAbsent(n.getNameAsString(), null);
            }
        }, null);
    }

    public void finalizeNames() {
        classMap.replaceAll((k, v) -> NameGenerator.generate("C"));
        methodMap.replaceAll((k, v) -> NameGenerator.generate("m"));
        varMap.replaceAll((k, v) -> NameGenerator.generate("v"));
    }

    public Map<String, String> getClassMap() {
        return classMap;
    }

    public Map<String, String> getMethodMap() {
        return methodMap;
    }

    public Map<String, String> getVarMap() {
        return varMap;
    }
}