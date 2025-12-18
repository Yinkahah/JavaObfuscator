package com.drawing.model;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, представляющий линию.
 * Линия определяется двумя точками.
 */
public class C_70b7fd extends C_66fb09 {

    private C_272058 v_3c02ae;

    private C_272058 v_c08d53;

    /**
     * Конструктор для создания линии.
     *
     * @param start начальная точка линии
     * @param end конечная точка линии
     * @param color цвет линии
     * @param lineWidth толщина линии
     */
    public C_70b7fd(C_272058 v_3c02ae, C_272058 v_c08d53, String v_7a285f, double v_00b9bc) {
        super(v_7a285f, v_00b9bc);
        this.v_3c02ae = v_3c02ae;
        this.v_c08d53 = v_c08d53;
    }

    @Override
    public List<C_272058> getPoints() {
        return Arrays.asList(v_3c02ae, v_c08d53);
    }

    @Override
    public String getType() {
        return "Line";
    }

    @Override
    public double getArea() {
        // Линия не имеет площади
        return 0;
    }

    @Override
    public boolean containsPoint(C_272058 v_e6e426) {
        // Проверяем, лежит ли точка на линии с учетом погрешности
        double v_2408fa = m_11bcaa(v_e6e426);
        // Погрешность 0.1
        return v_2408fa <= 0.1;
    }

    /**
     * Вычисляет расстояние от точки до линии.
     *
     * @param point точка
     * @return расстояние от точки до линии
     */
    private double m_11bcaa(C_272058 v_e6e426) {
        double v_d7752c = v_3c02ae.getX();
        double v_53e843 = v_3c02ae.getY();
        double v_dfa718 = v_c08d53.getX();
        double v_097ffc = v_c08d53.getY();
        double v_30c203 = v_e6e426.m_2d6360();
        double v_11e63c = v_e6e426.m_02b894();
        double v_67ad14 = Math.abs((v_097ffc - v_53e843) * v_30c203 - (v_dfa718 - v_d7752c) * v_11e63c + v_dfa718 * v_53e843 - v_097ffc * v_d7752c);
        double v_a78c8b = Math.sqrt(Math.pow(v_097ffc - v_53e843, 2) + Math.pow(v_dfa718 - v_d7752c, 2));
        return v_a78c8b == 0 ? 0 : v_67ad14 / v_a78c8b;
    }

    /**
     * Возвращает длину линии.
     *
     * @return длина линии
     */
    public double m_1958aa() {
        return v_3c02ae.distanceTo(v_c08d53);
    }

    /**
     * Возвращает начальную точку линии.
     *
     * @return начальная точка
     */
    public C_272058 m_eab391() {
        return v_3c02ae;
    }

    /**
     * Возвращает конечную точку линии.
     *
     * @return конечная точка
     */
    public C_272058 m_8b1bab() {
        return v_c08d53;
    }

    @Override
    public String toString() {
        return String.format("Line{start=%s, end=%s, color='%s', length=%.2f}", v_3c02ae, v_c08d53, v_7a285f, m_1958aa());
    }
}
