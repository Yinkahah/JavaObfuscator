package obfuscator;

import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class ObfuscatorApp {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  gradle run --args=\"<inputDir>\"                   — CLI-режим");
            System.out.println("  java -jar obfuscator.jar <inputDir>              — CLI из JAR");
            obfuscator.ui.ObfuscatorUI.main(new String[0]);
            return;
        }
        // Консольный режим (как у вас)
        Path inputRoot = Paths.get(args[0]).toAbsolutePath();
        Path outputRoot = Paths.get("obfuscated_output").toAbsolutePath();

        if (!Files.exists(inputRoot)) {
            System.err.println("❌ Path does not exist: " + inputRoot);
            return;
        }

        System.out.println("🔍 Сбор информации о классах и методах...");
        DeclarationCollector collector = new DeclarationCollector();

        try (Stream<Path> stream = Files.walk(inputRoot)) {
            List<Path> javaFiles = stream
                    .filter(p -> p.toString().endsWith(".java"))
                    .toList();

            for (Path p : javaFiles) {
                collector.collect(p);
            }
        }

        collector.finalizeNames();
        System.out.println("✅ Собрано: " +
                collector.getClassMap().size() + " классов, " +
                collector.getMethodMap().size() + " методов, " +
                collector.getVarMap().size() + " переменных");

        System.out.println("🔄 Запуск обфускации...");
        JavaObfuscator obfuscator = new JavaObfuscator(
                inputRoot,
                collector.getClassMap(),
                collector.getMethodMap(),
                collector.getVarMap(),
                collector.getObfSet()
        );

        try (Stream<Path> stream = Files.walk(inputRoot)) {
            List<Path> javaFiles = stream
                    .filter(p -> p.toString().endsWith(".java"))
                    .toList();

            for (int i = 0; i < javaFiles.size(); i++) {
                Path p = javaFiles.get(i);
                obfuscator.obfuscate(p, inputRoot, outputRoot);
                if (i % 10 == 0 || i == javaFiles.size() - 1) {
                    System.out.printf("📈 Обработано: %d/%d%n", i + 1, javaFiles.size());
                }
            }
        }

        System.out.println("✅ Obfuscation complete!");
        System.out.println("📁 Output: " + outputRoot.toAbsolutePath());
    }
}