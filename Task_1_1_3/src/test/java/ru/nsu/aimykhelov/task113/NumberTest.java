package ru.nsu.aimykhelov.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Number}.
 */
class NumberTest {

    /**
     * Проверяет вывод положительного числа.
     */
    @Test
    void testPrintPositive() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Number(42).print(new PrintStream(out));
        assertEquals("42", out.toString());
    }

    /**
     * Проверяет вывод нуля.
     */
    @Test
    void testPrintZero() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Number(0).print(new PrintStream(out));
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет вывод отрицательного числа.
     */
    @Test
    void testPrintNegative() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Number(-7).print(new PrintStream(out));
        assertEquals("-7", out.toString());
    }

    /**
     * Проверяет, что производная константы равна нулю.
     */
    @Test
    void testDerivativeIsZero() {
        Number n = new Number(5);
        Expression d = n.derivative("x");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        d.print(new PrintStream(out));
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет, что значение константы не зависит от означиваний.
     */
    @Test
    void testEval() {
        assertEquals(42, new Number(42).eval(""));
        assertEquals(42, new Number(42).eval("x = 10"));
        assertEquals(-3, new Number(-3).eval("x = 10; y = 20"));
    }
}