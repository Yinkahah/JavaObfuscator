package com.drawing.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Валидатор параметров генерации.
 * Проверяет корректность входных параметров для генерации рисунков.
 */
public class C_f857e7 {

    private static final Logger v_3c052f = LogManager.getLogger(C_f857e7.class);

    /**
     * Приватный конструктор для предотвращения создания экземпляров.
     */
    private C_f857e7() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Проверяет корректность параметров генерации.
     *
     * @param parameters параметры для проверки
     * @throws IllegalArgumentException если параметры некорректны
     */
    public static void m_2300cc(C_dee47e.C_5040e0 v_1815da) {
        if (v_1815da == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        m_aa0121(v_1815da.getShapeCount());
        m_c7997f(v_1815da.getMinX(), v_1815da.getMaxX(), "X");
        m_c7997f(v_1815da.getMinY(), v_1815da.getMaxY(), "Y");
        m_3bb680(v_1815da.getDensity());
        m_82291a(v_1815da.getGridSize());
        v_3c052f.debug("Parameters successfully validated: {}", v_1815da);
    }

    /**
     * Проверяет корректность количества фигур.
     */
    private static void m_aa0121(int v_ec31f7) {
        if (v_ec31f7 <= 0) {
            throw new IllegalArgumentException("Number of shapes must be positive");
        }
        if (v_ec31f7 > 1000) {
            throw new IllegalArgumentException("Number of shapes cannot exceed 1000");
        }
    }

    /**
     * Проверяет корректность координатного диапазона.
     */
    private static void m_c7997f(double v_28f474, double v_ae4f42, String v_576fe4) {
        if (v_28f474 >= v_ae4f42) {
            throw new IllegalArgumentException(String.format("Minimum %s value (%.2f) must be less than maximum (%.2f)", v_576fe4, v_28f474, v_ae4f42));
        }
        if (Math.abs(v_ae4f42 - v_28f474) < 1.0) {
            throw new IllegalArgumentException(String.format("%s axis range is too small (minimum range: 1.0)", v_576fe4));
        }
    }

    /**
     * Проверяет корректность значения кучности.
     */
    private static void m_3bb680(double v_fb0045) {
        if (v_fb0045 < 0.0 || v_fb0045 > 1.0) {
            throw new IllegalArgumentException("Density must be in range from 0.0 to 1.0");
        }
    }

    /**
     * Проверяет корректность размера сетки.
     */
    private static void m_82291a(int v_0fd46e) {
        if (v_0fd46e <= 0) {
            throw new IllegalArgumentException("Grid size must be positive");
        }
        if (v_0fd46e > 100) {
            throw new IllegalArgumentException("Grid size cannot exceed 100");
        }
    }
}
