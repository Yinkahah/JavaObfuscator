package com.drawing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий окружность.
 * Окружность определяется центром и радиусом.
 */
public class C_f718b0 extends C_66fb09 {

    private C_272058 v_a605df;

    private double v_92aa42;

    /**
     * Конструктор для создания окружности.
     *
     * @param center центр окружности
     * @param radius радиус окружности
     * @param color цвет окружности
     * @param lineWidth толщина линии
     */
    public C_f718b0(C_272058 v_a605df, double v_92aa42, String v_7a285f, double v_00b9bc) {
        super(v_7a285f, v_00b9bc);
        this.v_a605df = v_a605df;
        this.v_92aa42 = v_92aa42;
    }

    @Override
    public List<C_272058> getPoints() {
        // Для отрисовки используем 36 точек по окружности
        List<C_272058> v_1d7c8f = new ArrayList<>();
        int v_8918dc = 36;
        for (int v_feb622 = 0; v_feb622 < v_8918dc; v_feb622++) {
            double v_0df057 = 2 * Math.PI * v_feb622 / v_8918dc;
            double v_eba8ac = v_a605df.getX() + v_92aa42 * Math.cos(v_0df057);
            double v_af16f4 = v_a605df.getY() + v_92aa42 * Math.sin(v_0df057);
            v_1d7c8f.add(new C_272058(v_eba8ac, v_af16f4));
        }
        return v_1d7c8f;
    }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public double getArea() {
        return Math.PI * v_92aa42 * v_92aa42;
    }

    @Override
    public boolean containsPoint(C_272058 v_e6e426) {
        double v_2408fa = v_a605df.distanceTo(v_e6e426);
        return v_2408fa <= v_92aa42;
    }

    /**
     * Возвращает центр окружности.
     *
     * @return центр окружности
     */
    public C_272058 m_4c582d() {
        return v_a605df;
    }

    /**
     * Возвращает радиус окружности.
     *
     * @return радиус окружности
     */
    public double m_a27c86() {
        return v_92aa42;
    }

    /**
     * Возвращает длину окружности.
     *
     * @return длина окружности
     */
    public double m_d692c3() {
        return 2 * Math.PI * v_92aa42;
    }

    @Override
    public String toString() {
        return String.format("Circle{center=%s, radius=%.2f, color='%s', area=%.2f}", v_a605df, v_92aa42, v_7a285f, m_f83763());
    }
}
