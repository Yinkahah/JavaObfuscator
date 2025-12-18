package com.drawing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для классов геометрических фигур.
 */
class C_6e9083 {

    private C_272058 v_c3a774;

    private C_272058 v_4a6d2e;

    private C_272058 v_8e8527;

    private C_272058 v_293c33;

    @BeforeEach
    void m_4bb75a() {
        v_c3a774 = new C_272058(0, 0);
        v_4a6d2e = new C_272058(10, 0);
        v_8e8527 = new C_272058(10, 10);
        v_293c33 = new C_272058(0, 10);
    }

    @Test
    void m_14ea1c() {
        C_70b7fd v_6d3599 = new C_70b7fd(v_c3a774, v_8e8527, "#FF0000", 2.0);
        assertEquals("Line", v_6d3599.getType());
        assertEquals(2, v_6d3599.getPoints().size());
        assertEquals(0, v_6d3599.getArea());
        assertTrue(v_6d3599.getLength() > 0);
        assertEquals("#FF0000", v_6d3599.getColor());
        assertEquals(2.0, v_6d3599.getLineWidth());
    }

    @Test
    void m_206fed() {
        C_272058 v_a605df = new C_272058(5, 5);
        C_f718b0 v_701659 = new C_f718b0(v_a605df, 5, "#00FF00", 1.5);
        assertEquals("Circle", v_701659.getType());
        assertTrue(v_701659.getPoints().size() > 0);
        assertTrue(v_701659.getArea() > 0);
        assertEquals(5, v_701659.getRadius());
        assertEquals(v_a605df, v_701659.getCenter());
    }

    @Test
    void m_944019() {
        C_07f2c3 v_4bc3de = new C_07f2c3(v_c3a774, 10, 10, "#0000FF", 1.0);
        assertEquals("Rectangle", v_4bc3de.getType());
        assertEquals(4, v_4bc3de.getPoints().size());
        assertEquals(100, v_4bc3de.getArea());
        assertTrue(v_4bc3de.containsPoint(new C_272058(5, 5)));
        assertFalse(v_4bc3de.containsPoint(new C_272058(15, 15)));
    }

    @Test
    void m_818004() {
        C_2c2dac v_34f5df = new C_2c2dac(v_c3a774, v_4a6d2e, v_8e8527, "#FFFF00", 2.0);
        assertEquals("Triangle", v_34f5df.getType());
        assertEquals(3, v_34f5df.getPoints().size());
        assertTrue(v_34f5df.getArea() > 0);
        assertTrue(v_34f5df.containsPoint(new C_272058(5, 2)));
    }

    @Test
    void m_417d4c() {
        C_c030d5 v_f6437a = new C_c030d5(1, 0, 0, -5, 5, "#FF00FF", 1.5);
        assertEquals("Parabola", v_f6437a.getType());
        assertTrue(v_f6437a.getPoints().size() > 0);
        assertTrue(v_f6437a.getArea() > 0);
        assertNotNull(v_f6437a.getVertex());
    }

    @Test
    void m_709961() {
        C_5f52b9 v_d3c805 = new C_5f52b9(new C_272058(0, 0), new C_272058(8, 0), new C_272058(6, 6), new C_272058(2, 6), "#00FFFF", 1.0);
        assertEquals("Trapezoid", v_d3c805.getType());
        assertEquals(4, v_d3c805.getPoints().size());
        assertTrue(v_d3c805.getArea() > 0);
        assertTrue(v_d3c805.getPerimeter() > 0);
    }

    @Test
    void m_258325() {
        C_272058 v_507486 = new C_272058(0, 0);
        C_272058 v_6623c6 = new C_272058(3, 4);
        assertEquals(5, v_507486.distanceTo(v_6623c6), 0.001);
        assertEquals(v_507486.distanceTo(v_6623c6), v_6623c6.distanceTo(v_507486), 0.001);
    }

    @Test
    void m_b1459c() {
        C_70b7fd v_6d3599 = new C_70b7fd(v_c3a774, v_4a6d2e, "#123456", 3.0);
        assertEquals("#123456", v_6d3599.getColor());
        assertEquals(3.0, v_6d3599.getLineWidth());
        v_6d3599.setColor("#654321");
        v_6d3599.setLineWidth(2.0);
        assertEquals("#654321", v_6d3599.getColor());
        assertEquals(2.0, v_6d3599.getLineWidth());
    }
}
