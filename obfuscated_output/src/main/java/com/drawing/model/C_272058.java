package com.drawing.model;

import java.io.Serializable;

/**
 * Класс, представляющий точку в двумерном пространстве.
 */
public class C_272058 implements Serializable {

    private static final long v_d4dfd8 = 1L;

    private double v_eba8ac;

    private double v_af16f4;

    /**
     * Конструктор для создания точки с заданными координатами.
     *
     * @param x координата X
     * @param y координата Y
     */
    public C_272058(double v_eba8ac, double v_af16f4) {
        this.v_eba8ac = v_eba8ac;
        this.v_af16f4 = v_af16f4;
    }

    /**
     * Возвращает координату X точки.
     *
     * @return координата X
     */
    public double m_2d6360() {
        return v_eba8ac;
    }

    /**
     * Устанавливает координату X точки.
     *
     * @param x новая координата X
     */
    public void m_c7578c(double v_eba8ac) {
        this.v_eba8ac = v_eba8ac;
    }

    /**
     * Возвращает координату Y точки.
     *
     * @return координата Y
     */
    public double m_02b894() {
        return v_af16f4;
    }

    /**
     * Устанавливает координату Y точки.
     *
     * @param y новая координата Y
     */
    public void m_66826b(double v_af16f4) {
        this.v_af16f4 = v_af16f4;
    }

    /**
     * Вычисляет расстояние до другой точки.
     *
     * @param other другая точка
     * @return расстояние между точками
     */
    public double m_649f2e(C_272058 v_3869f9) {
        double v_741352 = this.v_eba8ac - v_3869f9.v_eba8ac;
        double v_5934c2 = this.v_af16f4 - v_3869f9.v_af16f4;
        return Math.sqrt(v_741352 * v_741352 + v_5934c2 * v_5934c2);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", v_eba8ac, v_af16f4);
    }

    @Override
    public boolean equals(Object v_d637bb) {
        if (this == v_d637bb)
            return true;
        if (v_d637bb == null || getClass() != v_d637bb.getClass())
            return false;
        C_272058 v_e6e426 = (C_272058) v_d637bb;
        return Double.compare(v_e6e426.v_eba8ac, v_eba8ac) == 0 && Double.compare(v_e6e426.v_af16f4, v_af16f4) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(v_eba8ac) * 31 + Double.hashCode(v_af16f4);
    }
}
