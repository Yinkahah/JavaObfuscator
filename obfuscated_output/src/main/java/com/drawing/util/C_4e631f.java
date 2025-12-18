package com.drawing.util;

/**
 * Утилитарный класс для математических операций.
 */
public class C_4e631f {

    /**
     * Приватный конструктор для предотвращения создания экземпляров.
     */
    private C_4e631f() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Ограничивает значение заданными пределами.
     *
     * @param value значение для ограничения
     * @param min минимальное допустимое значение
     * @param max максимальное допустимое значение
     * @return ограниченное значение
     */
    public static double m_545d7c(double v_2da678, double v_28f474, double v_ae4f42) {
        return Math.max(v_28f474, Math.min(v_ae4f42, v_2da678));
    }

    /**
     * Линейно интерполирует между двумя значениями.
     *
     * @param a начальное значение
     * @param b конечное значение
     * @param t коэффициент интерполяции (0.0-1.0)
     * @return интерполированное значение
     */
    public static double m_4bd125(double v_c82929, double v_fffcb2, double v_0b34e1) {
        return v_c82929 + (v_fffcb2 - v_c82929) * v_0b34e1;
    }

    /**
     * Преобразует координату из системы области в систему холста.
     *
     * @param value координата в системе области
     * @param minValue минимальное значение в системе области
     * @param maxValue максимальное значение в системе области
     * @param canvasSize размер холста
     * @return координата в системе холста
     */
    public static double m_6e7d4e(double v_2da678, double v_d97319, double v_75511d, double v_f1206b) {
        double v_03c9d0 = (v_2da678 - v_d97319) / (v_75511d - v_d97319);
        return v_03c9d0 * v_f1206b;
    }

    /**
     * Преобразует координату из системы холста в систему области.
     *
     * @param value координата в системе холста
     * @param minValue минимальное значение в системе области
     * @param maxValue максимальное значение в системе области
     * @param canvasSize размер холста
     * @return координата в системе области
     */
    public static double m_9aae15(double v_2da678, double v_d97319, double v_75511d, double v_f1206b) {
        double v_03c9d0 = v_2da678 / v_f1206b;
        return v_d97319 + v_03c9d0 * (v_75511d - v_d97319);
    }

    /**
     * Вычисляет расстояние между двумя точками.
     *
     * @param x1 координата X первой точки
     * @param y1 координата Y первой точки
     * @param x2 координата X второй точки
     * @param y2 координата Y второй точки
     * @return расстояние между точками
     */
    public static double m_47824e(double v_d7752c, double v_53e843, double v_dfa718, double v_097ffc) {
        double v_741352 = v_dfa718 - v_d7752c;
        double v_5934c2 = v_097ffc - v_53e843;
        return Math.sqrt(v_741352 * v_741352 + v_5934c2 * v_5934c2);
    }
}
