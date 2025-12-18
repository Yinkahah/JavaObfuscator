package com.drawing.generator;

import com.drawing.model.C_66fb09;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор случайных рисунков.
 */
public class C_dee47e {

    private static final Logger v_3c052f = LogManager.getLogger(C_dee47e.class);

    private final Random v_bda319;

    private final C_8db4ab v_606750;

    /**
     * Конструктор генератора рисунков.
     */
    public C_dee47e() {
        this.v_bda319 = new Random();
        this.v_606750 = new C_8db4ab();
        v_3c052f.info("Drawing generator initialized");
    }

    /**
     * Генерирует набор случайных фигур по заданным параметрам.
     * Использует все типы фигур.
     */
    public List<C_66fb09> m_09447c(C_5040e0 v_1815da) {
        return m_09447c(v_1815da, m_aa422d());
    }

    /**
     * Генерирует набор случайных фигур по заданным параметрам,
     * используя только указанные типы фигур.
     *
     * @param parameters параметры генерации
     * @param shapeTypes список типов фигур для генерации
     * @return список сгенерированных фигур
     * @throws IllegalArgumentException если параметры некорректны
     */
    public List<C_66fb09> m_09447c(C_5040e0 v_1815da, List<String> v_bb0647) {
        v_3c052f.info("Starting drawing generation with parameters: {}", v_1815da);
        v_3c052f.info("Selected shape types: {}", v_bb0647);
        // Валидация параметров
        C_f857e7.m_2300cc(v_1815da);
        // Проверяем, что есть выбранные типы фигур
        if (v_bb0647 == null || v_bb0647.isEmpty()) {
            throw new IllegalArgumentException("No shape types selected for generation");
        }
        List<C_66fb09> v_f8af0e = new ArrayList<>();
        int v_ec31f7 = v_1815da.getShapeCount();
        double v_8b8bd0 = v_1815da.getMinX();
        double v_56f9ac = v_1815da.getMaxX();
        double v_8c1511 = v_1815da.getMinY();
        double v_72060f = v_1815da.getMaxY();
        double v_fb0045 = v_1815da.getDensity();
        // Учет кучности - сужение области для более плотного расположения
        double v_44ab6e = (v_8b8bd0 + v_56f9ac) / 2;
        double v_292a12 = (v_8c1511 + v_72060f) / 2;
        double v_085a08 = (v_56f9ac - v_8b8bd0) * v_fb0045;
        double v_312564 = (v_72060f - v_8c1511) * v_fb0045;
        double v_eb3390 = v_44ab6e - v_085a08 / 2;
        double v_c6d6c4 = v_44ab6e + v_085a08 / 2;
        double v_0e7e1a = v_292a12 - v_312564 / 2;
        double v_fb22c7 = v_292a12 + v_312564 / 2;
        v_3c052f.info("Effective generation area: x=[{}, {}], y=[{}, {}]", v_eb3390, v_c6d6c4, v_0e7e1a, v_fb22c7);
        // Генерация фигур только выбранных типов
        for (int v_feb622 = 0; i < v_ec31f7; v_feb622++) {
            try {
                C_66fb09 v_025693 = m_3b9e96(v_bb0647, v_eb3390, v_c6d6c4, v_0e7e1a, v_fb22c7);
                if (v_025693 != null) {
                    v_f8af0e.add(v_025693);
                    if (i < 10) {
                        // Логируем только первые 10 фигур для отладки
                        v_3c052f.debug("Generated shape #{}/{}: {}", i + 1, v_ec31f7, v_025693.getType());
                    }
                } else {
                    v_3c052f.warn("Failed to generate shape #{}/{}", i + 1, v_ec31f7);
                }
            } catch (Exception v_8ed4d3) {
                v_3c052f.error("Error generating shape #{}/{}: {}", i + 1, v_ec31f7, v_8ed4d3.getMessage());
                // Продолжаем генерацию остальных фигур
            }
        }
        v_3c052f.info("Generation completed. Successfully created {} shapes out of {} requested", v_f8af0e.size(), v_ec31f7);
        return v_f8af0e;
    }

    /**
     * Генерирует одну случайную фигуру из указанных типов.
     */
    private C_66fb09 m_3b9e96(List<String> v_bb0647, double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f) {
        // Выбор случайного типа фигуры из выбранных
        if (v_bb0647.isEmpty()) {
            v_3c052f.error("Shape types list is empty!");
            return null;
        }
        String v_0db051 = v_bb0647.get(v_bda319.nextInt(v_bb0647.size()));
        C_8db4ab.ShapeType v_3598ce;
        try {
            v_3598ce = C_8db4ab.ShapeType.valueOf(v_0db051);
        } catch (IllegalArgumentException v_8ed4d3) {
            v_3c052f.error("Unknown shape type: {}. Using LINE as default", v_0db051);
            // Используем линию как тип по умолчанию
            v_3598ce = C_8db4ab.ShapeType.LINE;
        }
        // Генерация случайного цвета
        String v_7a285f = m_ddf626();
        // Генерация случайной толщины линии
        // От 1.0 до 4.0
        double v_00b9bc = 1.0 + v_bda319.nextDouble() * 3.0;
        try {
            // Создание фигуры через фабрику
            return v_606750.createShape(v_3598ce, v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
        } catch (Exception v_8ed4d3) {
            v_3c052f.error("Error creating shape type {}: {}", v_3598ce, v_8ed4d3.getMessage());
            return null;
        }
    }

    /**
     * Генерирует случайный цвет в формате HEX.
     *
     * @return цвет в формате #RRGGBB
     */
    private String m_ddf626() {
        int v_77730b = v_bda319.nextInt(256);
        int v_acdaac = v_bda319.nextInt(256);
        int v_fffcb2 = v_bda319.nextInt(256);
        return String.format("#%02X%02X%02X", v_77730b, v_acdaac, v_fffcb2);
    }

    /**
     * Возвращает список всех доступных типов фигур.
     *
     * @return список всех типов фигур
     */
    private List<String> m_aa422d() {
        List<String> v_ed9fbd = new ArrayList<>();
        for (C_8db4ab.ShapeType v_5c1f99 : C_8db4ab.ShapeType.values()) {
            v_ed9fbd.add(v_5c1f99.name());
        }
        return v_ed9fbd;
    }

    public static class C_5040e0 {

        private final int v_ec31f7;

        private final double v_8b8bd0;

        private final double v_56f9ac;

        private final double v_8c1511;

        private final double v_72060f;

        private final double v_fb0045;

        private final int v_0fd46e;

        /**
         * Конструктор параметров генерации.
         *
         * @param shapeCount количество фигур
         * @param minX минимальная координата X
         * @param maxX максимальная координата X
         * @param minY минимальная координата Y
         * @param maxY максимальная координата Y
         * @param density кучность фигур (0.0-1.0)
         * @param gridSize размер координатной сетки
         */
        public C_5040e0(int v_ec31f7, double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, double v_fb0045, int v_0fd46e) {
            this.v_ec31f7 = v_ec31f7;
            this.v_8b8bd0 = v_8b8bd0;
            this.v_56f9ac = v_56f9ac;
            this.v_8c1511 = v_8c1511;
            this.v_72060f = v_72060f;
            this.v_fb0045 = v_fb0045;
            this.v_0fd46e = v_0fd46e;
        }

        public int m_d88322() {
            return v_ec31f7;
        }

        public double m_4ce2be() {
            return v_8b8bd0;
        }

        public double m_b77fdc() {
            return v_56f9ac;
        }

        public double m_d00e89() {
            return v_8c1511;
        }

        public double m_5a5609() {
            return v_72060f;
        }

        public double m_026f25() {
            return v_fb0045;
        }

        public int m_7a2176() {
            return v_0fd46e;
        }

        @Override
        public String toString() {
            return String.format("GenerationParameters{shapeCount=%d, x=[%.1f, %.1f], y=[%.1f, %.1f], density=%.2f, gridSize=%d}", v_ec31f7, v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_fb0045, v_0fd46e);
        }
    }
}
