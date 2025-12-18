package com.drawing.model;

import java.util.List;

/**
 * Абстрактный класс, представляющий геометрическую фигуру.
 * Содержит общие свойства и методы для всех фигур.
 */
public abstract class C_66fb09 {

    protected String v_7a285f;

    protected double v_00b9bc;

    /**
     * Конструктор для создания фигуры.
     *
     * @param color цвет фигуры в формате HEX
     * @param lineWidth толщина линии
     */
    public C_66fb09(String v_7a285f, double v_00b9bc) {
        this.v_7a285f = v_7a285f;
        this.v_00b9bc = v_00b9bc;
    }

    /**
     * Возвращает список точек, определяющих фигуру.
     *
     * @return список точек фигуры
     */
    public abstract List<C_272058> m_3ea28d();

    /**
     * Возвращает тип фигуры.
     *
     * @return строковое представление типа фигуры
     */
    public abstract String m_7f8a64();

    /**
     * Возвращает площадь фигуры.
     *
     * @return площадь фигуры
     */
    public abstract double m_f83763();

    /**
     * Проверяет, содержит ли фигура указанную точку.
     *
     * @param point точка для проверки
     * @return true если точка находится внутри фигуры, иначе false
     */
    public abstract boolean m_45ab25(C_272058 v_e6e426);

    /**
     * Возвращает цвет фигуры.
     *
     * @return цвет в формате HEX
     */
    public String m_6b905c() {
        return v_7a285f;
    }

    /**
     * Устанавливает цвет фигуры.
     *
     * @param color новый цвет в формате HEX
     */
    public void m_504876(String v_7a285f) {
        this.v_7a285f = v_7a285f;
    }

    /**
     * Возвращает толщину линии фигуры.
     *
     * @return толщина линии
     */
    public double m_0288e5() {
        return v_00b9bc;
    }

    /**
     * Устанавливает толщину линии фигуры.
     *
     * @param lineWidth новая толщина линии
     */
    public void m_2c6aa7(double v_00b9bc) {
        this.v_00b9bc = v_00b9bc;
    }

    @Override
    public String toString() {
        return String.format("%s{color='%s', lineWidth=%.1f}", m_7f8a64(), v_7a285f, v_00b9bc);
    }
}
