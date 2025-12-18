package com.drawing.model;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, представляющий прямоугольник.
 * Прямоугольник определяется верхней левой точкой, шириной и высотой.
 */
public class C_07f2c3 extends C_66fb09 {

    private C_272058 v_e75b01;

    private double v_085a08;

    private double v_312564;

    /**
     * Конструктор для создания прямоугольника.
     *
     * @param topLeft верхняя левая точка прямоугольника
     * @param width ширина прямоугольника
     * @param height высота прямоугольника
     * @param color цвет прямоугольника
     * @param lineWidth толщина линии
     */
    public C_07f2c3(C_272058 v_e75b01, double v_085a08, double v_312564, String v_7a285f, double v_00b9bc) {
        super(v_7a285f, v_00b9bc);
        this.v_e75b01 = v_e75b01;
        this.v_085a08 = v_085a08;
        this.v_312564 = v_312564;
    }

    @Override
    public List<C_272058> getPoints() {
        C_272058 v_b6e62f = new C_272058(v_e75b01.m_2d6360() + v_085a08, v_e75b01.m_02b894());
        C_272058 v_d3cea3 = new C_272058(v_e75b01.m_2d6360(), v_e75b01.m_02b894() + v_312564);
        C_272058 v_d2769f = new C_272058(v_e75b01.m_2d6360() + v_085a08, v_e75b01.m_02b894() + v_312564);
        return Arrays.asList(v_e75b01, v_b6e62f, v_d2769f, v_d3cea3);
    }

    @Override
    public String getType() {
        return "Rectangle";
    }

    @Override
    public double getArea() {
        return v_085a08 * v_312564;
    }

    @Override
    public boolean containsPoint(C_272058 v_e6e426) {
        double v_eba8ac = v_e6e426.m_2d6360();
        double v_af16f4 = v_e6e426.m_02b894();
        double v_34c5f5 = v_e75b01.m_2d6360();
        double v_203ee2 = v_34c5f5 + v_085a08;
        double v_8c0c5c = v_e75b01.m_02b894();
        double v_3e942d = v_8c0c5c + v_312564;
        return v_eba8ac >= v_34c5f5 && v_eba8ac <= v_203ee2 && v_af16f4 >= v_8c0c5c && v_af16f4 <= v_3e942d;
    }

    /**
     * Возвращает верхнюю левую точку прямоугольника.
     *
     * @return верхняя левая точка
     */
    public C_272058 m_6d362e() {
        return v_e75b01;
    }

    /**
     * Возвращает ширину прямоугольника.
     *
     * @return ширина прямоугольника
     */
    public double m_ca2ad6() {
        return v_085a08;
    }

    /**
     * Возвращает высоту прямоугольника.
     *
     * @return высота прямоугольника
     */
    public double m_28dc5b() {
        return v_312564;
    }

    /**
     * Вычисляет периметр прямоугольника.
     *
     * @return периметр прямоугольника
     */
    public double m_389368() {
        return 2 * (v_085a08 + v_312564);
    }

    @Override
    public String toString() {
        return String.format("Rectangle{topLeft=%s, width=%.2f, height=%.2f, color='%s', area=%.2f}", v_e75b01, v_085a08, v_312564, v_7a285f, m_f83763());
    }
}
