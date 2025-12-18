package com.drawing.generator;

import com.drawing.model.*;
import java.util.Random;

/**
 * Фабрика для создания геометрических фигур.
 * Создает случайные фигуры в заданных пределах.
 */
public class C_8db4ab {

    private final Random v_bda319;

    /**
     * Конструктор фабрики фигур.
     */
    public C_8db4ab() {
        this.v_bda319 = new Random();
    }

    /**
     * Создает случайную фигуру заданного типа в пределах указанных координат.
     *
     * @param type тип фигуры
     * @param minX минимальная координата X
     * @param maxX максимальная координата X
     * @param minY минимальная координата Y
     * @param maxY максимальная координата Y
     * @param color цвет фигуры
     * @param lineWidth толщина линии
     * @return созданная фигура
     */
    public C_66fb09 m_066956(ShapeType v_5c1f99, double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        switch(v_5c1f99) {
            case LINE:
                return m_5aeccb(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
            case CIRCLE:
                return m_d31d40(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
            case RECTANGLE:
                return m_5c886e(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
            case TRIANGLE:
                return m_0b2627(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
            case PARABOLA:
                return m_f71760(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
            case TRAPEZOID:
                return m_010bcb(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
            default:
                // В случае неизвестного типа создаем линию
                return m_5aeccb(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f, v_7a285f, v_00b9bc);
        }
    }

    /**
     * Создает случайную линию.
     */
    private C_70b7fd m_5aeccb(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        C_272058 v_3c02ae = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        C_272058 v_c08d53 = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        return new C_70b7fd(v_3c02ae, v_c08d53, v_7a285f, v_00b9bc);
    }

    /**
     * Создает случайную окружность.
     */
    private C_f718b0 m_d31d40(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        C_272058 v_a605df = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        // Ограничиваем максимальный радиус
        double v_dc2ef7 = Math.min(v_56f9ac - v_a605df.getX(), v_a605df.getX() - v_8b8bd0);
        double v_1a7e7d = Math.min(v_72060f - v_a605df.getY(), v_a605df.getY() - v_8c1511);
        double v_8dee49 = Math.min(v_dc2ef7, v_1a7e7d);
        // Радиус от 2 до половины доступного пространства
        double v_92aa42 = 2.0 + v_bda319.nextDouble() * (v_8dee49 / 2 - 2.0);
        // Минимальный радиус 2.0
        v_92aa42 = Math.max(v_92aa42, 2.0);
        return new C_f718b0(v_a605df, v_92aa42, v_7a285f, v_00b9bc);
    }

    /**
     * Создает случайный прямоугольник.
     */
    private C_07f2c3 m_5c886e(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        C_272058 v_e75b01 = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        double v_ac0875 = v_56f9ac - v_e75b01.m_2d6360();
        double v_990207 = v_72060f - v_e75b01.m_02b894();
        // Минимальные размеры 5x5
        double v_83dd41 = 5.0;
        if (v_ac0875 < v_83dd41 || v_990207 < v_83dd41) {
            // Если не хватает места, создаем точку заново
            v_e75b01 = m_c3e685(v_8b8bd0, v_56f9ac - v_83dd41, v_8c1511, v_72060f - v_83dd41);
            v_ac0875 = v_56f9ac - v_e75b01.m_2d6360();
            v_990207 = v_72060f - v_e75b01.m_02b894();
        }
        double v_085a08 = v_83dd41 + v_bda319.nextDouble() * (v_ac0875 - v_83dd41);
        double v_312564 = v_83dd41 + v_bda319.nextDouble() * (v_990207 - v_83dd41);
        return new C_07f2c3(v_e75b01, v_085a08, v_312564, v_7a285f, v_00b9bc);
    }

    /**
     * Создает случайный треугольник.
     */
    private C_2c2dac m_0b2627(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        C_272058 v_507486 = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        C_272058 v_6623c6 = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        C_272058 v_50bc8e = m_c3e685(v_8b8bd0, v_56f9ac, v_8c1511, v_72060f);
        return new C_2c2dac(v_507486, v_6623c6, v_50bc8e, v_7a285f, v_00b9bc);
    }

    /**
     * Создает случайную параболу.
     */
    private C_c030d5 m_f71760(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        // От -1 до 1
        double v_c82929 = (v_bda319.nextDouble() - 0.5) * 2;
        // От -2 до 2
        double v_fffcb2 = (v_bda319.nextDouble() - 0.5) * 4;
        double v_877c97 = v_8c1511 + v_bda319.nextDouble() * (v_72060f - v_8c1511);
        // Ограничиваем диапазон x для отрисовки
        double v_e5d493 = v_56f9ac - v_8b8bd0;
        double v_da5363 = v_8b8bd0 + v_e5d493 * 0.1;
        double v_290c37 = v_56f9ac - v_e5d493 * 0.1;
        return new C_c030d5(v_c82929, v_fffcb2, v_877c97, v_da5363, v_290c37, v_7a285f, v_00b9bc);
    }

    /**
     * Создает случайную трапецию.
     */
    private C_5f52b9 m_010bcb(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f, String v_7a285f, double v_00b9bc) {
        double v_085a08 = v_56f9ac - v_8b8bd0;
        double v_312564 = v_72060f - v_8c1511;
        // Верхнее основание
        double v_af5fd8 = v_8c1511 + v_312564 * 0.2 + v_bda319.nextDouble() * v_312564 * 0.3;
        double v_252542 = v_8b8bd0 + v_085a08 * 0.1 + v_bda319.nextDouble() * v_085a08 * 0.3;
        double v_0f2ca6 = v_252542 + v_085a08 * 0.2 + v_bda319.nextDouble() * v_085a08 * 0.3;
        // Нижнее основание
        double v_2d7adb = v_af5fd8 + v_312564 * 0.3 + v_bda319.nextDouble() * v_312564 * 0.3;
        double v_9b9c98 = v_8b8bd0 + v_085a08 * 0.2 + v_bda319.nextDouble() * v_085a08 * 0.2;
        double v_34196e = v_9b9c98 + v_085a08 * 0.3 + v_bda319.nextDouble() * v_085a08 * 0.2;
        C_272058 v_e75b01 = new C_272058(v_252542, v_af5fd8);
        C_272058 v_b6e62f = new C_272058(v_0f2ca6, v_af5fd8);
        C_272058 v_d2769f = new C_272058(v_34196e, v_2d7adb);
        C_272058 v_d3cea3 = new C_272058(v_9b9c98, v_2d7adb);
        return new C_5f52b9(v_e75b01, v_b6e62f, v_d2769f, v_d3cea3, v_7a285f, v_00b9bc);
    }

    /**
     * Создает случайную точку в заданных пределах.
     */
    private C_272058 m_c3e685(double v_8b8bd0, double v_56f9ac, double v_8c1511, double v_72060f) {
        double v_eba8ac = v_8b8bd0 + v_bda319.nextDouble() * (v_56f9ac - v_8b8bd0);
        double v_af16f4 = v_8c1511 + v_bda319.nextDouble() * (v_72060f - v_8c1511);
        return new C_272058(v_eba8ac, v_af16f4);
    }

    /**
     * Перечисление типов фигур.
     */
    public enum ShapeType {

        LINE,
        CIRCLE,
        RECTANGLE,
        TRIANGLE,
        PARABOLA,
        TRAPEZOID
    }
}
