package com.drawing;

import com.drawing.gui.C_f1f5fc;
import java.util.Locale;

/**
 * Main application class
 */
public class C_9ab74a {

    public static void main(String[] v_27cbcc) {
        // Устанавливаем локаль US для гарантированного использования точки как десятичного разделителя
        Locale.setDefault(Locale.US);
        // Устанавливаем системные свойства для кодировки
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("sun.stdout.encoding", "UTF-8");
        System.setProperty("sun.stderr.encoding", "UTF-8");
        System.setProperty("sun.jnu.encoding", "UTF-8");
        // Запускаем приложение
        C_f1f5fc.main(v_27cbcc);
    }
}
