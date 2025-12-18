package obfuscator.ui;

import obfuscator.DeclarationCollector;
import obfuscator.JavaObfuscator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class ObfuscatorUI extends Application {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Java Obfuscator");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label targetLabel = new Label("Выберите папку или .java-файл:");
        TextField targetPathField = new TextField();
        targetPathField.setEditable(false);
        targetPathField.setPromptText("Путь к файлу или папке...");

        Button browseButton = new Button("Обзор...");
        browseButton.setOnAction(e -> browseForTarget(primaryStage, targetPathField));

        Button startButton = new Button("▶ Запустить обфускацию");
        startButton.setStyle("-fx-font-weight: bold;");

        ProgressBar progressBar = new ProgressBar();
        progressBar.setVisible(false);

        Label statusLabel = new Label("Готово.");
        statusLabel.setStyle("-fx-text-fill: green;");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                titleLabel,
                targetLabel,
                targetPathField,
                browseButton,
                startButton,
                progressBar,
                statusLabel
        );

        startButton.setOnAction(e -> {
            String pathStr = targetPathField.getText().trim();
            if (pathStr.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Ошибка", "Путь не выбран.");
                return;
            }

            Path targetPath = Paths.get(pathStr).toAbsolutePath();
            if (!Files.exists(targetPath)) {
                showAlert(Alert.AlertType.ERROR, "Ошибка", "Путь не существует: " + targetPath);
                return;
            }

            startButton.setDisable(true);
            browseButton.setDisable(true);
            progressBar.setVisible(true);
            progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
            statusLabel.setText("Сбор информации...");
            statusLabel.setStyle("-fx-text-fill: orange;");

            executor.submit(() -> runObfuscation(targetPath, progressBar, statusLabel, startButton, browseButton));
        });

        Scene scene = new Scene(layout, 520, 300);
        primaryStage.setTitle("Java Obfuscator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void browseForTarget(Stage owner, TextField field) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Выберите папку с .java-файлами");
        File dir = dirChooser.showDialog(owner);
        if (dir != null) {
            field.setText(dir.getAbsolutePath());
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите .java-файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Files", "*.java"));
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            field.setText(file.getAbsolutePath());
        }
    }

    private void runObfuscation(
            Path targetPath,
            ProgressBar progressBar,
            Label statusLabel,
            Button startButton,
            Button browseButton) {

        try {
            Path inputRoot = Files.isDirectory(targetPath) ? targetPath : targetPath.getParent();
            if (inputRoot == null) throw new IllegalArgumentException("Невозможно определить корень");

            Path outputRoot = inputRoot.resolve("obfuscated_output").toAbsolutePath();

            // Этап 1: сбор
            updateStatus(statusLabel, "Сбор имён классов и методов...");
            DeclarationCollector collector = new DeclarationCollector();
            try (Stream<Path> stream = Files.walk(inputRoot)) {
                List<Path> javaFiles = stream.filter(p -> p.toString().endsWith(".java")).toList();
                for (Path p : javaFiles) collector.collect(p);
            }
            collector.finalizeNames();

            // Этап 2: обфускация
            updateStatus(statusLabel, "Обфускация...");
            JavaObfuscator obfuscator = new JavaObfuscator(
                    inputRoot,
                    collector.getClassMap(),
                    collector.getMethodMap(),
                    collector.getVarMap(),
                    collector.getObfSet()
            );

            try (Stream<Path> stream = Files.walk(inputRoot)) {
                List<Path> javaFiles = stream.filter(p -> p.toString().endsWith(".java")).toList();
                for (int i = 0; i < javaFiles.size(); i++) {
                    Path p = javaFiles.get(i);
                    obfuscator.obfuscate(p, inputRoot, outputRoot);

                    final int progress = i + 1;
                    final int total = javaFiles.size();
                    Platform.runLater(() -> {
                        progressBar.setProgress((double) progress / total);
                        statusLabel.setText("Обработано: " + progress + "/" + total);
                    });
                }
            }

            Platform.runLater(() -> {
                progressBar.setVisible(false);
                statusLabel.setText("✅ Готово! Результат: " + outputRoot.toAbsolutePath().getFileName());
                statusLabel.setStyle("-fx-text-fill: green;");
                showAlert(Alert.AlertType.INFORMATION, "Успех", "Результат в папке:\nobfuscated_output");
            });

        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                statusLabel.setText("❌ Ошибка: " + e.getMessage());
                statusLabel.setStyle("-fx-text-fill: red;");
                showAlert(Alert.AlertType.ERROR, "Ошибка", e.toString());
            });
        } finally {
            Platform.runLater(() -> {
                startButton.setDisable(false);
                browseButton.setDisable(false);
            });
        }
    }

    private void updateStatus(Label label, String text) {
        Platform.runLater(() -> {
            label.setText(text);
            label.setStyle("-fx-text-fill: orange;");
        });
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    @Override
    public void stop() {
        executor.shutdownNow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}