package com.drawing.generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для валидатора параметров.
 */
class C_8fa284 {

    @Test
    void m_f68395() {
        C_dee47e.C_5040e0 v_1815da = new C_dee47e.C_5040e0(10, -100, 100, -100, 100, 0.5, 10);
        // Не должно быть исключения
        assertDoesNotThrow(() -> C_f857e7.m_2300cc(v_1815da));
    }

    @Test
    void m_f8dd6a() {
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(null);
        });
    }

    @Test
    void m_825e4f() {
        // Отрицательное количество фигур
        C_dee47e.C_5040e0 v_c04c4e = new C_dee47e.C_5040e0(-5, -100, 100, -100, 100, 0.5, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_c04c4e);
        });
        // Слишком большое количество фигур
        C_dee47e.C_5040e0 v_cd6b7e = new C_dee47e.C_5040e0(2000, -100, 100, -100, 100, 0.5, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_cd6b7e);
        });
    }

    @Test
    void m_2f8839() {
        // minX >= maxX
        C_dee47e.C_5040e0 v_c04c4e = new C_dee47e.C_5040e0(10, 100, 50, -100, 100, 0.5, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_c04c4e);
        });
        // minY >= maxY
        C_dee47e.C_5040e0 v_cd6b7e = new C_dee47e.C_5040e0(10, -100, 100, 100, 50, 0.5, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_cd6b7e);
        });
        // Слишком маленький диапазон
        C_dee47e.C_5040e0 v_14a75c = new C_dee47e.C_5040e0(10, 0, 0.5, 0, 0.5, 0.5, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_14a75c);
        });
    }

    @Test
    void m_ca36bf() {
        // Плотность < 0
        C_dee47e.C_5040e0 v_c04c4e = new C_dee47e.C_5040e0(10, -100, 100, -100, 100, -0.1, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_c04c4e);
        });
        // Плотность > 1
        C_dee47e.C_5040e0 v_cd6b7e = new C_dee47e.C_5040e0(10, -100, 100, -100, 100, 1.1, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_cd6b7e);
        });
    }

    @Test
    void m_746539() {
        // Размер сетки <= 0
        C_dee47e.C_5040e0 v_c04c4e = new C_dee47e.C_5040e0(10, -100, 100, -100, 100, 0.5, -5);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_c04c4e);
        });
        // Слишком большой размер сетки
        C_dee47e.C_5040e0 v_cd6b7e = new C_dee47e.C_5040e0(10, -100, 100, -100, 100, 0.5, 200);
        assertThrows(IllegalArgumentException.class, () -> {
            C_f857e7.m_2300cc(v_cd6b7e);
        });
    }
}
