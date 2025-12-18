package com.drawing.model;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, представляющий трапецию.
 * Трапеция определяется четырьмя точками.
 */
public class C_5f52b9 extends C_66fb09 {

    private C_272058 v_e75b01;

    private C_272058 v_b6e62f;

    private C_272058 v_d2769f;

    private C_272058 v_d3cea3;

    /**
     * Конструктор для создания трапеции.
     *
     * @param topLeft верхняя левая точка
     * @param topRight верхняя правая точка
     * @param bottomRight нижняя правая точка
     * @param bottomLeft нижняя левая точка
     * @param color цвет трапеции
     * @param lineWidth толщина линии
     */
    public C_5f52b9(C_272058 v_e75b01, C_272058 v_b6e62f, C_272058 v_d2769f, C_272058 v_d3cea3, String v_7a285f, double v_00b9bc) {
        super(v_7a285f, v_00b9bc);
        this.v_e75b01 = v_e75b01;
        this.v_b6e62f = v_b6e62f;
        this.v_d2769f = v_d2769f;
        this.v_d3cea3 = v_d3cea3;
    }

    @Override
    public List<C_272058> getPoints() {
        return Arrays.asList(v_e75b01, v_b6e62f, v_d2769f, v_d3cea3);
    }

    @Override
    public String getType() {
        return "Trapezoid";
    }

    @Override
    public double getArea() {
        // Формула площади трапеции через координаты вершин
        double v_29672f = 0;
        // Используем формулу площади многоугольника через координаты вершин
        List<C_272058> v_1d7c8f = m_3ea28d();
        int v_6b094f = v_1d7c8f.size();
        for (int v_feb622 = 0; v_feb622 < v_6b094f; v_feb622++) {
            C_272058 v_da0f9a = v_1d7c8f.get(v_feb622);
            C_272058 v_2ea196 = v_1d7c8f.get((v_feb622 + 1) % v_6b094f);
            v_29672f += v_da0f9a.getX() * v_2ea196.getY() - v_2ea196.getX() * v_da0f9a.getY();
        }
        return Math.abs(v_29672f) / 2.0;
    }

    @Override
    public boolean containsPoint(C_272058 v_e6e426) {
        // Разбиваем трапецию на два треугольника и проверяем принадлежность
        C_2c2dac v_a4342d = new C_2c2dac(v_e75b01, v_b6e62f, v_d2769f, v_7a285f, v_00b9bc);
        C_2c2dac v_652a10 = new C_2c2dac(v_e75b01, v_d2769f, v_d3cea3, v_7a285f, v_00b9bc);
        return v_a4342d.containsPoint(v_e6e426) || v_652a10.containsPoint(v_e6e426);
    }

    /**
     * Возвращает верхнюю левую точку трапеции.
     *
     * @return верхняя левая точка
     */
    public C_272058 m_6d362e() {
        return v_e75b01;
    }

    /**
     * Возвращает верхнюю правую точку трапеции.
     *
     * @return верхняя правая точка
     */
    public C_272058 m_ce9016() {
        return v_b6e62f;
    }

    /**
     * Возвращает нижнюю правую точку трапеции.
     *
     * @return нижняя правая точка
     */
    public C_272058 m_627d44() {
        return v_d2769f;
    }

    /**
     * Возвращает нижнюю левую точку трапеции.
     *
     * @return нижняя левая точка
     */
    public C_272058 m_9ee967() {
        return v_d3cea3;
    }

    /**
     * Вычисляет периметр трапеции.
     *
     * @return периметр трапеции
     */
    public double m_389368() {
        double v_035a82 = v_e75b01.m_649f2e(v_b6e62f);
        double v_064992 = v_b6e62f.m_649f2e(v_d2769f);
        double v_731084 = v_d2769f.m_649f2e(v_d3cea3);
        double v_2e0ea7 = v_d3cea3.m_649f2e(v_e75b01);
        return v_035a82 + v_064992 + v_731084 + v_2e0ea7;
    }

    @Override
    public String toString() {
        return String.format("Trapezoid{points=[%s, %s, %s, %s], color='%s', area=%.2f}", v_e75b01, v_b6e62f, v_d2769f, v_d3cea3, v_7a285f, m_f83763());
    }
}
