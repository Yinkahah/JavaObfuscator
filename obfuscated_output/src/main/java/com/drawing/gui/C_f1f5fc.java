package com.drawing.gui;

import com.drawing.generator.C_dee47e;
import com.drawing.generator.C_f857e7;
import com.drawing.model.C_272058;
import com.drawing.model.C_66fb09;
import com.drawing.util.C_4e631f;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

/**
 * Графический интерфейс приложения генерации рисунков.
 */
public class C_f1f5fc extends Application {

    private static final Logger v_3c052f = LogManager.getLogger(C_f1f5fc.class);

    private static final int v_dde337 = 1200;

    private static final int v_b62a6d = 800;

    private static final int v_1bed18 = 800;

    private static final int v_7d18bf = 600;

    private Canvas v_ac44be;

    private GraphicsContext v_764acb;

    private List<C_66fb09> v_3aaba1 = new ArrayList<>();

    private C_dee47e v_8873ae;

    // Текущие границы отображения
    private double v_e9a8d4 = -100;

    private double v_d13d2b = 100;

    private double v_a1386f = -100;

    private double v_8a1d23 = 100;

    // Элементы управления
    private TextField v_dead4e;

    private TextField v_74fa92;

    private TextField v_8dc261;

    private TextField v_f5975f;

    private TextField v_2e9409;

    private Slider v_3edd9a;

    private TextField v_a4ae24;

    private Label v_7ce164;

    // Чекбоксы для выбора фигур
    private CheckBox v_1f1ccb;

    private CheckBox v_139ae4;

    private CheckBox v_3acb4d;

    private CheckBox v_315ee2;

    private CheckBox v_86bbaa;

    private CheckBox v_1eb7c7;

    /**
     * Точка входа для запуска графического интерфейса.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] v_27cbcc) {
        launch(v_27cbcc);
    }

    @Override
    public void start(Stage v_c8db2b) {
        v_3c052f.info("Starting graphical interface application");
        try {
            v_8873ae = new C_dee47e();
            // Инициализируем элементы управления ПЕРЕД их использованием
            m_568169();
            m_fec3b7(v_c8db2b);
            v_c8db2b.show();
            v_3c052f.info("Graphical interface successfully initialized");
        } catch (Exception v_8ed4d3) {
            v_3c052f.error("Error starting graphical interface: {}", v_8ed4d3.getMessage(), v_8ed4d3);
            m_f6dd8b("Ошибка запуска", "Не удалось запустить приложение", v_8ed4d3.getMessage());
        }
    }

    /**
     * Инициализирует элементы управления.
     */
    private void m_568169() {
        // Инициализируем все элементы управления здесь
        v_dead4e = new TextField("20");
        v_74fa92 = new TextField("-100");
        v_8dc261 = new TextField("100");
        v_f5975f = new TextField("-100");
        v_2e9409 = new TextField("100");
        v_3edd9a = m_9142ec();
        v_a4ae24 = new TextField("10");
        v_7ce164 = new Label("Готов к работе");
        // Инициализируем чекбоксы для выбора фигур
        v_1f1ccb = new CheckBox("Линия");
        v_139ae4 = new CheckBox("Окружность");
        v_3acb4d = new CheckBox("Прямоугольник");
        v_315ee2 = new CheckBox("Треугольник");
        v_86bbaa = new CheckBox("Парабола");
        v_1eb7c7 = new CheckBox("Трапеция");
        // Устанавливаем все фигуры выбранными по умолчанию
        v_1f1ccb.setSelected(true);
        v_139ae4.setSelected(true);
        v_3acb4d.setSelected(true);
        v_315ee2.setSelected(true);
        v_86bbaa.setSelected(true);
        v_1eb7c7.setSelected(true);
        // Настраиваем размеры
        v_dead4e.setPrefWidth(150);
        v_74fa92.setPrefWidth(150);
        v_8dc261.setPrefWidth(150);
        v_f5975f.setPrefWidth(150);
        v_2e9409.setPrefWidth(150);
        v_a4ae24.setPrefWidth(150);
    }

    /**
     * Инициализирует пользовательский интерфейс.
     */
    private void m_fec3b7(Stage v_f40827) {
        v_f40827.setTitle("Генератор случайных рисунков");
        // Основной контейнер
        BorderPane v_92cb04 = new BorderPane();
        v_92cb04.setPadding(new Insets(10));
        // Панель управления
        VBox v_22cdf7 = m_973d65();
        v_92cb04.setLeft(v_22cdf7);
        // Область отображения
        VBox v_ba8c8c = m_64f3f6();
        v_92cb04.setCenter(v_ba8c8c);
        // Панель статуса
        HBox v_54b5ba = m_7a993f();
        v_92cb04.setBottom(v_54b5ba);
        Scene v_598777 = new Scene(v_92cb04, v_dde337, v_b62a6d);
        v_f40827.setScene(v_598777);
        v_f40827.setMinWidth(v_dde337);
        v_f40827.setMinHeight(v_b62a6d);
        v_f40827.setOnCloseRequest(v_385218 -> {
            v_3c052f.info("Application shutdown");
            System.exit(0);
        });
    }

    /**
     * Создает панель управления.
     */
    private VBox m_973d65() {
        VBox v_22cdf7 = new VBox(10);
        v_22cdf7.setPadding(new Insets(10));
        v_22cdf7.setPrefWidth(350);
        v_22cdf7.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 1;");
        // Заголовок
        Label v_59f9c5 = new Label("Параметры генерации");
        v_59f9c5.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        // Панель выбора фигур
        VBox v_e8e752 = m_e4f53a();
        // Кнопки управления
        HBox v_e687a1 = new HBox(10);
        v_e687a1.setAlignment(Pos.CENTER);
        Button v_467018 = new Button("Сгенерировать");
        v_467018.setPrefWidth(120);
        v_467018.setOnAction(v_8ed4d3 -> m_2fd002());
        Button v_29e3e4 = new Button("Очистить");
        v_29e3e4.setPrefWidth(120);
        v_29e3e4.setOnAction(v_8ed4d3 -> m_751e01());
        v_e687a1.getChildren().addAll(v_467018, v_29e3e4);
        // Добавление элементов на панель
        v_22cdf7.getChildren().addAll(v_59f9c5, m_1ed7e3("Количество фигур:", v_dead4e), m_1ed7e3("Минимальный X:", v_74fa92), m_1ed7e3("Максимальный X:", v_8dc261), m_1ed7e3("Минимальный Y:", v_f5975f), m_1ed7e3("Максимальный Y:", v_2e9409), m_1ed7e3("Кучность (0.0-1.0):", v_3edd9a), m_1ed7e3("Размер сетки:", v_a4ae24), new Separator(), new Label("Выберите типы фигур:"), v_e8e752, new Separator(), v_e687a1);
        return v_22cdf7;
    }

    /**
     * Создает панель для выбора фигур.
     */
    private VBox m_e4f53a() {
        VBox v_e8e752 = new VBox(5);
        v_e8e752.setPadding(new Insets(10));
        v_e8e752.setStyle("-fx-background-color: #e8f4f8; -fx-border-color: #b0d4e0; -fx-border-width: 1;");
        // Первая строка чекбоксов
        HBox v_e1fa82 = new HBox(10);
        v_e1fa82.getChildren().addAll(v_1f1ccb, v_139ae4, v_3acb4d);
        // Вторая строка чекбоксов
        HBox v_ea911a = new HBox(10);
        v_ea911a.getChildren().addAll(v_315ee2, v_86bbaa, v_1eb7c7);
        // Кнопки для быстрого выбора
        HBox v_fafc7c = new HBox(10);
        v_fafc7c.setAlignment(Pos.CENTER);
        Button v_ab3d3f = new Button("Все");
        v_ab3d3f.setOnAction(v_8ed4d3 -> m_39efd7(true));
        Button v_0de9f0 = new Button("Ничего");
        v_0de9f0.setOnAction(v_8ed4d3 -> m_39efd7(false));
        Button v_62df87 = new Button("Случайно");
        v_62df87.setOnAction(v_8ed4d3 -> m_4050c2());
        v_fafc7c.getChildren().addAll(v_ab3d3f, v_0de9f0, v_62df87);
        v_e8e752.getChildren().addAll(v_e1fa82, v_ea911a, new Separator(), v_fafc7c);
        return v_e8e752;
    }

    /**
     * Создает область отображения рисунка.
     */
    private VBox m_64f3f6() {
        VBox v_ba8c8c = new VBox(10);
        v_ba8c8c.setPadding(new Insets(10));
        v_ba8c8c.setAlignment(Pos.CENTER);
        // Холст для рисования
        v_ac44be = new Canvas(v_1bed18, v_7d18bf);
        v_764acb = v_ac44be.getGraphicsContext2D();
        // Панель инструментов для холста
        HBox v_ff99e8 = new HBox(10);
        v_ff99e8.setAlignment(Pos.CENTER);
        Button v_b9d072 = new Button("Сетка");
        v_b9d072.setOnAction(v_8ed4d3 -> m_0893bd());
        Button v_a3ebae = new Button("+");
        v_a3ebae.setOnAction(v_8ed4d3 -> m_e931ef(1.2));
        Button v_5db76c = new Button("-");
        v_5db76c.setOnAction(v_8ed4d3 -> m_e931ef(0.8));
        Button v_0674dd = new Button("Сброс");
        v_0674dd.setOnAction(v_8ed4d3 -> m_2e2b1a());
        v_ff99e8.getChildren().addAll(v_b9d072, v_a3ebae, v_5db76c, v_0674dd);
        v_ba8c8c.getChildren().addAll(v_ff99e8, v_ac44be);
        // Очистка холста при запуске
        m_751e01();
        return v_ba8c8c;
    }

    /**
     * Создает панель статуса.
     */
    private HBox m_7a993f() {
        HBox v_54b5ba = new HBox(10);
        v_54b5ba.setPadding(new Insets(5, 10, 5, 10));
        v_54b5ba.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #dddddd; -fx-border-width: 1 0 0 0;");
        // statusLabel уже инициализирован в initializeControls()
        v_7ce164.setStyle("-fx-text-fill: #333333;");
        ProgressIndicator v_978964 = new ProgressIndicator();
        v_978964.setVisible(false);
        v_978964.setPrefSize(16, 16);
        v_54b5ba.getChildren().addAll(v_7ce164, new Region(), v_978964);
        HBox.setHgrow(v_54b5ba.getChildren().get(1), Priority.ALWAYS);
        return v_54b5ba;
    }

    /**
     * Создает слайдер для регулировки кучности.
     */
    private Slider m_9142ec() {
        Slider v_6bd176 = new Slider(0.0, 1.0, 0.5);
        v_6bd176.setShowTickLabels(true);
        v_6bd176.setShowTickMarks(true);
        v_6bd176.setMajorTickUnit(0.25);
        v_6bd176.setMinorTickCount(5);
        v_6bd176.setPrefWidth(150);
        return v_6bd176;
    }

    /**
     * Создает контейнер с меткой и элементом управления.
     */
    private HBox m_1ed7e3(String v_29443b, Control v_a9f158) {
        HBox v_d1f9f5 = new HBox(10);
        v_d1f9f5.setAlignment(Pos.CENTER_LEFT);
        Label v_57f831 = new Label(v_29443b);
        v_57f831.setPrefWidth(150);
        v_d1f9f5.getChildren().addAll(v_57f831, v_a9f158);
        return v_d1f9f5;
    }

    /**
     * Выбирает или снимает выбор со всех фигур.
     */
    private void m_39efd7(boolean v_6a0620) {
        v_1f1ccb.setSelected(v_6a0620);
        v_139ae4.setSelected(v_6a0620);
        v_3acb4d.setSelected(v_6a0620);
        v_315ee2.setSelected(v_6a0620);
        v_86bbaa.setSelected(v_6a0620);
        v_1eb7c7.setSelected(v_6a0620);
        m_3397e3(v_6a0620 ? "Все фигуры выбраны" : "Все фигуры сняты", "#666666");
    }

    /**
     * Случайным образом выбирает фигуры.
     */
    private void m_4050c2() {
        Random v_bda319 = new Random();
        v_1f1ccb.setSelected(v_bda319.nextBoolean());
        v_139ae4.setSelected(v_bda319.nextBoolean());
        v_3acb4d.setSelected(v_bda319.nextBoolean());
        v_315ee2.setSelected(v_bda319.nextBoolean());
        v_86bbaa.setSelected(v_bda319.nextBoolean());
        v_1eb7c7.setSelected(v_bda319.nextBoolean());
        m_3397e3("Фигуры выбраны случайным образом", "#666666");
    }

    /**
     * Получает список выбранных типов фигур.
     */
    private List<String> m_98f1ca() {
        List<String> v_a084dc = new ArrayList<>();
        if (v_1f1ccb.isSelected())
            v_a084dc.add("LINE");
        if (v_139ae4.isSelected())
            v_a084dc.add("CIRCLE");
        if (v_3acb4d.isSelected())
            v_a084dc.add("RECTANGLE");
        if (v_315ee2.isSelected())
            v_a084dc.add("TRIANGLE");
        if (v_86bbaa.isSelected())
            v_a084dc.add("PARABOLA");
        if (v_1eb7c7.isSelected())
            v_a084dc.add("TRAPEZOID");
        return v_a084dc;
    }

    /**
     * Генерирует новый рисунок.
     */
    private void m_2fd002() {
        try {
            v_3c052f.info("=== STARTING DRAWING GENERATION ===");
            m_3397e3("Генерация...", "#FFA500");
            // Проверяем, что выбрана хотя бы одна фигура
            List<String> v_a084dc = m_98f1ca();
            v_3c052f.info("Selected shape types count: {}", v_a084dc.size());
            if (v_a084dc.isEmpty()) {
                v_3c052f.error("No shapes selected!");
                m_3397e3("Ошибка: не выбрано ни одной фигуры", "#FF0000");
                m_f6dd8b("Ошибка выбора", "Не выбрано ни одной фигуры", "Пожалуйста, выберите хотя бы один тип фигуры для генерации.");
                return;
            }
            // Получение параметров из полей ввода - исправляем проблему с запятой
            String v_75af5a = m_dfe3a0(v_dead4e.getText());
            String v_188d32 = m_dfe3a0(v_74fa92.getText());
            String v_24f85b = m_dfe3a0(v_8dc261.getText());
            String v_97cbb2 = m_dfe3a0(v_f5975f.getText());
            String v_71b552 = m_dfe3a0(v_2e9409.getText());
            String v_f80f73 = m_dfe3a0(v_a4ae24.getText());
            v_3c052f.debug("Normalized inputs - shapeCount: {}, minX: {}, maxX: {}, minY: {}, maxY: {}, gridSize: {}", v_75af5a, v_188d32, v_24f85b, v_97cbb2, v_71b552, v_f80f73);
            int v_ec31f7 = Integer.parseInt(v_75af5a);
            v_e9a8d4 = Double.parseDouble(v_188d32);
            v_d13d2b = Double.parseDouble(v_24f85b);
            v_a1386f = Double.parseDouble(v_97cbb2);
            v_8a1d23 = Double.parseDouble(v_71b552);
            double v_fb0045 = v_3edd9a.getValue();
            int v_0fd46e = Integer.parseInt(v_f80f73);
            v_3c052f.info("Parameters: shapes={}, X=[{}, {}], Y=[{}, {}], density={}, grid={}", v_ec31f7, v_e9a8d4, v_d13d2b, v_a1386f, v_8a1d23, v_fb0045, v_0fd46e);
            // Проверяем корректность границ
            if (v_e9a8d4 >= v_d13d2b) {
                throw new IllegalArgumentException("Минимальный X должен быть меньше максимального X");
            }
            if (v_a1386f >= v_8a1d23) {
                throw new IllegalArgumentException("Минимальный Y должен быть меньше максимального Y");
            }
            // Создание параметров генерации
            C_dee47e.C_5040e0 v_1815da = new C_dee47e.C_5040e0(v_ec31f7, v_e9a8d4, v_d13d2b, v_a1386f, v_8a1d23, v_fb0045, v_0fd46e);
            // Валидация параметров
            C_f857e7.m_2300cc(v_1815da);
            // Генерация фигур
            v_3c052f.info("Starting generation of {} shapes...", v_ec31f7);
            v_3aaba1 = v_8873ae.m_09447c(v_1815da, v_a084dc);
            v_3c052f.info("Shapes generated. Received: {} shapes", v_3aaba1.size());
            if (v_3aaba1.isEmpty()) {
                v_3c052f.warn("Failed to generate any shapes!");
                m_3397e3("Не удалось сгенерировать фигуры", "#FF0000");
                return;
            }
            // Отображаем информацию о первых фигурах для отладки
            for (int v_feb622 = 0; v_feb622 < Math.min(3, v_3aaba1.size()); v_feb622++) {
                C_66fb09 v_025693 = v_3aaba1.get(v_feb622);
                List<C_272058> v_1d7c8f = v_025693.getPoints();
                v_3c052f.debug("Shape {}: type={}, color={}, points={}, lineWidth={}", v_feb622 + 1, v_025693.getType(), v_025693.getColor(), v_1d7c8f.size(), v_025693.getLineWidth());
            }
            // Очищаем холст и рисуем
            m_af5c83();
            m_11843d();
            m_f70018(v_0fd46e);
            v_3c052f.info("=== GENERATION AND RENDERING COMPLETED ===");
            m_3397e3(String.format("Сгенерировано %d фигур", v_3aaba1.size()), "#008000");
        } catch (NumberFormatException v_8ed4d3) {
            v_3c052f.error("Number format error: {}. Please use dot (.) as decimal separator", v_8ed4d3.getMessage());
            m_3397e3("Ошибка ввода данных", "#FF0000");
            m_f6dd8b("Ошибка ввода", "Некорректный формат числа", "Пожалуйста, используйте точку (.) как десятичный разделитель.\nПример: 10.5 вместо 10,5");
        } catch (IllegalArgumentException v_8ed4d3) {
            v_3c052f.error("Parameter validation error: {}", v_8ed4d3.getMessage());
            m_3397e3("Ошибка параметров", "#FF0000");
            m_f6dd8b("Ошибка параметров", "Некорректные параметры", v_8ed4d3.getMessage());
        } catch (Exception v_8ed4d3) {
            v_3c052f.error("Unexpected error during generation: {}", v_8ed4d3.getMessage(), v_8ed4d3);
            m_3397e3("Ошибка генерации", "#FF0000");
            m_f6dd8b("Ошибка", "Ошибка при генерации", v_8ed4d3.getMessage());
        }
    }

    /**
     * Нормализует числовую строку - заменяет запятые на точки и удаляет лишние пробелы.
     */
    private String m_dfe3a0(String v_006b77) {
        if (v_006b77 == null || v_006b77.trim().isEmpty()) {
            return "0";
        }
        return v_006b77.trim().replace(',', '.');
    }

    /**
     * Отрисовывает все фигуры на холсте.
     */
    private void m_11843d() {
        v_3c052f.debug("Starting shapes rendering. Count: {}", v_3aaba1.size());
        if (v_3aaba1.isEmpty()) {
            v_3c052f.warn("No shapes to render!");
            return;
        }
        int v_4ba239 = 0;
        for (C_66fb09 v_025693 : v_3aaba1) {
            try {
                m_c301ce(v_025693);
                v_4ba239++;
            } catch (Exception v_8ed4d3) {
                v_3c052f.error("Error rendering shape {}: {}", v_025693.getType(), v_8ed4d3.getMessage());
            }
        }
        v_3c052f.debug("Rendered {} shapes out of {}", v_4ba239, v_3aaba1.size());
    }

    /**
     * Отрисовывает одну фигуру на холсте.
     */
    private void m_c301ce(C_66fb09 v_025693) {
        v_764acb.setStroke(Color.web(v_025693.getColor()));
        v_764acb.setLineWidth(v_025693.getLineWidth());
        var v_1d7c8f = v_025693.getPoints();
        if (v_1d7c8f.isEmpty()) {
            return;
        }
        // Преобразование первой точки
        double v_490f1d = m_6df898(v_1d7c8f.get(0).getX());
        double v_84b937 = m_43f98c(v_1d7c8f.get(0).getY());
        // Начало пути
        v_764acb.beginPath();
        v_764acb.moveTo(v_490f1d, v_84b937);
        // Добавление остальных точек
        for (int v_feb622 = 1; v_feb622 < v_1d7c8f.size(); v_feb622++) {
            double v_eba8ac = m_6df898(v_1d7c8f.get(v_feb622).getX());
            double v_af16f4 = m_43f98c(v_1d7c8f.get(v_feb622).getY());
            v_764acb.lineTo(v_eba8ac, v_af16f4);
        }
        // Замыкание пути для замкнутых фигур
        if (!v_025693.getType().equals("Line") && !v_025693.getType().equals("Parabola")) {
            v_764acb.lineTo(v_490f1d, v_84b937);
        }
        // Отрисовка
        v_764acb.stroke();
    }

    /**
     * Преобразует координату X из системы области в систему холста.
     */
    private double m_6df898(double v_eba8ac) {
        return C_4e631f.m_6e7d4e(v_eba8ac, v_e9a8d4, v_d13d2b, v_1bed18);
    }

    /**
     * Преобразует координату Y из системы области в систему холста.
     */
    private double m_43f98c(double v_af16f4) {
        double v_03c9d0 = (v_af16f4 - v_a1386f) / (v_8a1d23 - v_a1386f);
        // Инверсия для правильной ориентации
        return v_7d18bf - (v_03c9d0 * v_7d18bf);
    }

    /**
     * Отрисовывает координатную сетку.
     */
    private void m_f70018(int v_0fd46e) {
        v_764acb.setStroke(Color.LIGHTGRAY);
        v_764acb.setLineWidth(0.5);
        // Вертикальные линии
        double v_0adb11 = (v_d13d2b - v_e9a8d4) / v_0fd46e;
        for (int v_feb622 = 0; v_feb622 <= v_0fd46e; v_feb622++) {
            double v_eba8ac = v_e9a8d4 + v_feb622 * v_0adb11;
            double v_c6938b = m_6df898(v_eba8ac);
            v_764acb.beginPath();
            v_764acb.moveTo(v_c6938b, 0);
            v_764acb.lineTo(v_c6938b, v_7d18bf);
            v_764acb.stroke();
            // Подписи
            if (v_c6938b > 20 && v_c6938b < v_1bed18 - 40) {
                v_764acb.setFill(Color.DARKGRAY);
                v_764acb.fillText(String.format("%.1f", v_eba8ac), v_c6938b + 2, v_7d18bf - 2);
            }
        }
        // Горизонтальные линии
        double v_cfc84b = (v_8a1d23 - v_a1386f) / v_0fd46e;
        for (int v_feb622 = 0; v_feb622 <= v_0fd46e; v_feb622++) {
            double v_af16f4 = v_a1386f + v_feb622 * v_cfc84b;
            double v_0023cd = m_43f98c(v_af16f4);
            v_764acb.beginPath();
            v_764acb.moveTo(0, v_0023cd);
            v_764acb.lineTo(v_1bed18, v_0023cd);
            v_764acb.stroke();
            // Подписи
            if (v_0023cd > 20 && v_0023cd < v_7d18bf - 20) {
                v_764acb.setFill(Color.DARKGRAY);
                v_764acb.fillText(String.format("%.1f", v_af16f4), 2, v_0023cd - 2);
            }
        }
        // Оси координат
        v_764acb.setStroke(Color.BLACK);
        v_764acb.setLineWidth(2);
        double v_cc11ed = m_6df898(0);
        double v_4ea81a = m_43f98c(0);
        if (v_cc11ed >= 0 && v_cc11ed <= v_1bed18) {
            v_764acb.beginPath();
            v_764acb.moveTo(v_cc11ed, 0);
            v_764acb.lineTo(v_cc11ed, v_7d18bf);
            v_764acb.stroke();
        }
        if (v_4ea81a >= 0 && v_4ea81a <= v_7d18bf) {
            v_764acb.beginPath();
            v_764acb.moveTo(0, v_4ea81a);
            v_764acb.lineTo(v_1bed18, v_4ea81a);
            v_764acb.stroke();
        }
    }

    /**
     * Очищает холст.
     */
    private void m_751e01() {
        v_764acb.setFill(Color.WHITE);
        v_764acb.fillRect(0, 0, v_1bed18, v_7d18bf);
        v_3aaba1.clear();
        m_3397e3("Холст очищен", "#666666");
    }

    /**
     * Очищает холст для перерисовки.
     */
    private void m_af5c83() {
        v_764acb.setFill(Color.WHITE);
        v_764acb.fillRect(0, 0, v_1bed18, v_7d18bf);
    }

    /**
     * Переключает отображение сетки.
     */
    private void m_0893bd() {
        // Для отображения сетки сгенерируйте новый рисунок
        m_3397e3("Для отображения сетки сгенерируйте новый рисунок", "#666666");
    }

    /**
     * Изменяет масштаб холста.
     */
    private void m_e931ef(double v_b21e81) {
        try {
            v_3c052f.debug("Zooming canvas with factor: {}", v_b21e81);
            // Сохраняем центр текущего вида
            double v_44ab6e = (v_e9a8d4 + v_d13d2b) / 2;
            double v_292a12 = (v_a1386f + v_8a1d23) / 2;
            // Вычисляем новые границы
            double v_085a08 = (v_d13d2b - v_e9a8d4) / v_b21e81;
            double v_312564 = (v_8a1d23 - v_a1386f) / v_b21e81;
            // Проверяем минимальные размеры
            if (v_085a08 < 1.0 || v_312564 < 1.0) {
                v_3c052f.warn("Cannot zoom further - minimum size reached");
                m_3397e3("Достигнут минимальный масштаб", "#FFA500");
                return;
            }
            v_e9a8d4 = v_44ab6e - v_085a08 / 2;
            v_d13d2b = v_44ab6e + v_085a08 / 2;
            v_a1386f = v_292a12 - v_312564 / 2;
            v_8a1d23 = v_292a12 + v_312564 / 2;
            // Обновляем поля ввода
            v_74fa92.setText(String.format("%.1f", v_e9a8d4));
            v_8dc261.setText(String.format("%.1f", v_d13d2b));
            v_f5975f.setText(String.format("%.1f", v_a1386f));
            v_2e9409.setText(String.format("%.1f", v_8a1d23));
            // Перерисовываем
            m_af5c83();
            m_11843d();
            // Рисуем сетку с текущим размером
            String v_f80f73 = m_dfe3a0(v_a4ae24.getText());
            try {
                int v_0fd46e = Integer.parseInt(v_f80f73);
                m_f70018(v_0fd46e);
            } catch (NumberFormatException v_8ed4d3) {
                // Значение по умолчанию
                m_f70018(10);
            }
            m_3397e3(String.format("Масштаб изменен: x∈[%.1f, %.1f], y∈[%.1f, %.1f]", v_e9a8d4, v_d13d2b, v_a1386f, v_8a1d23), "#666666");
        } catch (Exception v_8ed4d3) {
            v_3c052f.error("Error during zoom: {}", v_8ed4d3.getMessage());
            m_3397e3("Ошибка масштабирования", "#FF0000");
        }
    }

    /**
     * Сбрасывает вид холста.
     */
    private void m_2e2b1a() {
        v_e9a8d4 = -100;
        v_d13d2b = 100;
        v_a1386f = -100;
        v_8a1d23 = 100;
        v_74fa92.setText("-100");
        v_8dc261.setText("100");
        v_f5975f.setText("-100");
        v_2e9409.setText("100");
        m_751e01();
        m_3397e3("Вид сброшен", "#666666");
    }

    /**
     * Обновляет статусную строку.
     */
    private void m_3397e3(String v_576cef, String v_7a285f) {
        if (v_7ce164 != null) {
            v_7ce164.setText(v_576cef);
            v_7ce164.setStyle(String.format("-fx-text-fill: %s;", v_7a285f));
        }
    }

    /**
     * Показывает диалоговое окно с ошибкой.
     */
    private void m_f6dd8b(String v_b2c88b, String v_71d0b5, String v_5ae588) {
        Alert v_377c26 = new Alert(Alert.AlertType.ERROR);
        v_377c26.setTitle(v_b2c88b);
        v_377c26.setHeaderText(v_71d0b5);
        v_377c26.setContentText(v_5ae588);
        v_377c26.showAndWait();
    }
}
