package com.drawing.model;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, представляющий треугольник.
 * Треугольник определяется тремя точками.
 */
public class C_2c2dac extends C_66fb09 {

    private C_272058 v_c3a774;

    private C_272058 v_4a6d2e;

    private C_272058 v_8e8527;

    /**
     * Конструктор для создания треугольника.
     *
     * @param point1 первая точка треугольника
     * @param point2 вторая точка треугольника
     * @param point3 третья точка треугольника
     * @param color цвет треугольника
     * @param lineWidth толщина линии
     */
    public C_2c2dac(C_272058 v_c3a774, C_272058 v_4a6d2e, C_272058 v_8e8527, String v_7a285f, double v_00b9bc) {
        super(v_7a285f, v_00b9bc);
        this.v_c3a774 = v_c3a774;
        this.v_4a6d2e = v_4a6d2e;
        this.v_8e8527 = v_8e8527;
    }

    @Override
    public List<C_272058> getPoints() {
        return Arrays.asList(v_c3a774, v_4a6d2e, v_8e8527);
    }

    @Override
    public String getType() {
        return "Triangle";
    }

    @Override
    public double getArea() {
        // Формула площади треугольника через координаты вершин
        double v_29672f = Math.abs(v_c3a774.m_2d6360() * (v_4a6d2e.m_02b894() - v_8e8527.m_02b894()) + v_4a6d2e.m_2d6360() * (v_8e8527.m_02b894() - v_c3a774.m_02b894()) + v_8e8527.m_2d6360() * (v_c3a774.m_02b894() - v_4a6d2e.m_02b894())) / 2.0;
        return v_29672f;
    }

    @Override
    public boolean containsPoint(C_272058 v_e6e426) {
        // Метод барицентрических координат
        double v_eba8ac = v_e6e426.m_2d6360();
        double v_af16f4 = v_e6e426.m_02b894();
        double v_d7752c = v_c3a774.m_2d6360();
        double v_53e843 = v_c3a774.m_02b894();
        double v_dfa718 = v_4a6d2e.m_2d6360();
        double v_097ffc = v_4a6d2e.m_02b894();
        double v_236f12 = v_8e8527.m_2d6360();
        double v_4e2708 = v_8e8527.m_02b894();
        double v_a78c8b = ((v_097ffc - v_4e2708) * (v_d7752c - v_236f12) + (v_236f12 - v_dfa718) * (v_53e843 - v_4e2708));
        double v_c82929 = ((v_097ffc - v_4e2708) * (v_eba8ac - v_236f12) + (v_236f12 - v_dfa718) * (v_af16f4 - v_4e2708)) / v_a78c8b;
        double v_fffcb2 = ((v_4e2708 - v_53e843) * (v_eba8ac - v_236f12) + (v_d7752c - v_236f12) * (v_af16f4 - v_4e2708)) / v_a78c8b;
        double v_877c97 = 1 - v_c82929 - v_fffcb2;
        return v_c82929 >= 0 && v_c82929 <= 1 && v_fffcb2 >= 0 && v_fffcb2 <= 1 && v_877c97 >= 0 && v_877c97 <= 1;
    }

    /**
     * Возвращает первую точку треугольника.
     *
     * @return первая точка
     */
    public C_272058 m_04b5ca() {
        return v_c3a774;
    }

    /**
     * Возвращает вторую точку треугольника.
     *
     * @return вторая точка
     */
    public C_272058 m_9ccdc2() {
        return v_4a6d2e;
    }

    /**
     * Возвращает третью точку треугольника.
     *
     * @return третья точка
     */
    public C_272058 m_23ea27() {
        return v_8e8527;
    }

    /**
     * Вычисляет периметр треугольника.
     *
     * @return периметр треугольника
     */
    public double m_389368() {
        double v_035a82 = v_c3a774.m_649f2e(v_4a6d2e);
        double v_064992 = v_4a6d2e.m_649f2e(v_8e8527);
        double v_731084 = v_8e8527.m_649f2e(v_c3a774);
        return v_035a82 + v_064992 + v_731084;
    }

    @Override
    public String toString() {
        return String.format("Triangle{points=[%s, %s, %s], color='%s', area=%.2f}", v_c3a774, v_4a6d2e, v_8e8527, v_7a285f, m_f83763());
    }
}
