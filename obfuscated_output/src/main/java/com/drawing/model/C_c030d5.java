package com.drawing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий параболу.
 * Парабола определяется уравнением y = a*x^2 + b*x + c.
 */
public class C_c030d5 extends C_66fb09 {

    private double v_c82929;

    private double v_fffcb2;

    private double v_877c97;

    private double v_689c56;

    private double v_c3aaf8;

    /**
     * Конструктор для создания параболы.
     *
     * @param a коэффициент при x^2
     * @param b коэффициент при x
     * @param c свободный коэффициент
     * @param xMin минимальное значение x для отрисовки
     * @param xMax максимальное значение x для отрисовки
     * @param color цвет параболы
     * @param lineWidth толщина линии
     */
    public C_c030d5(double v_c82929, double v_fffcb2, double v_877c97, double v_689c56, double v_c3aaf8, String v_7a285f, double v_00b9bc) {
        super(v_7a285f, v_00b9bc);
        this.v_c82929 = v_c82929;
        this.v_fffcb2 = v_fffcb2;
        this.v_877c97 = v_877c97;
        this.v_689c56 = v_689c56;
        this.v_c3aaf8 = v_c3aaf8;
    }

    @Override
    public List<C_272058> getPoints() {
        List<C_272058> v_1d7c8f = new ArrayList<>();
        // Количество точек для аппроксимации
        int v_8918dc = 100;
        double v_1fa91c = (v_c3aaf8 - v_689c56) / v_8918dc;
        for (int v_feb622 = 0; v_feb622 <= v_8918dc; v_feb622++) {
            double v_eba8ac = v_689c56 + v_feb622 * v_1fa91c;
            double v_af16f4 = v_c82929 * v_eba8ac * v_eba8ac + v_fffcb2 * v_eba8ac + v_877c97;
            v_1d7c8f.add(new C_272058(v_eba8ac, v_af16f4));
        }
        return v_1d7c8f;
    }

    @Override
    public String getType() {
        return "Parabola";
    }

    @Override
    public double getArea() {
        // Приближенная площадь под параболой на интервале [xMin, xMax]
        int v_8918dc = 1000;
        double v_1fa91c = (v_c3aaf8 - v_689c56) / v_8918dc;
        double v_29672f = 0;
        for (int v_feb622 = 0; v_feb622 < v_8918dc; v_feb622++) {
            double v_d7752c = v_689c56 + v_feb622 * v_1fa91c;
            double v_dfa718 = v_d7752c + v_1fa91c;
            double v_53e843 = v_c82929 * v_d7752c * v_d7752c + v_fffcb2 * v_d7752c + v_877c97;
            double v_097ffc = v_c82929 * v_dfa718 * v_dfa718 + v_fffcb2 * v_dfa718 + v_877c97;
            // Метод трапеций
            v_29672f += (v_53e843 + v_097ffc) * v_1fa91c / 2;
        }
        return Math.abs(v_29672f);
    }

    @Override
    public boolean containsPoint(C_272058 v_e6e426) {
        double v_eba8ac = v_e6e426.m_2d6360();
        double v_af16f4 = v_e6e426.m_02b894();
        // Проверяем, лежит ли точка на параболе с погрешностью
        double v_5c3d30 = v_c82929 * v_eba8ac * v_eba8ac + v_fffcb2 * v_eba8ac + v_877c97;
        // Погрешность 0.5
        return Math.abs(v_af16f4 - v_5c3d30) <= 0.5;
    }

    /**
     * Возвращает коэффициент a параболы.
     *
     * @return коэффициент a
     */
    public double m_3a8b79() {
        return v_c82929;
    }

    /**
     * Возвращает коэффициент b параболы.
     *
     * @return коэффициент b
     */
    public double m_ebfe15() {
        return v_fffcb2;
    }

    /**
     * Возвращает коэффициент c параболы.
     *
     * @return коэффициент c
     */
    public double m_883893() {
        return v_877c97;
    }

    /**
     * Возвращает вершину параболы.
     *
     * @return точка вершины параболы
     */
    public C_272058 m_7df9b3() {
        double v_926bcd = -v_fffcb2 / (2 * v_c82929);
        double v_2bff9d = v_c82929 * v_926bcd * v_926bcd + v_fffcb2 * v_926bcd + v_877c97;
        return new C_272058(v_926bcd, v_2bff9d);
    }

    @Override
    public String toString() {
        return String.format("Parabola{y=%.2fx² + %.2fx + %.2f, x∈[%.1f,%.1f], color='%s'}", v_c82929, v_fffcb2, v_877c97, v_689c56, v_c3aaf8, v_7a285f);
    }
}
