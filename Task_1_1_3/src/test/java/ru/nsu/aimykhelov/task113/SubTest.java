package ru.nsu.aimykhelov.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Sub}.
 */
class SubTest {

    /**
     * Проверяет печать разности.
     */
    @Test
    void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Sub(new Number(10), new Number(4)).print(new PrintStream(out));
        assertEquals("(10-4)", out.toString());
    }

    /**
     * Проверяет вычисление разности.
     */
    @Test
    void testEval() {
        assertEquals(6, new Sub(new Number(10), new Number(4)).eval(""));
    }

    /**
     * Проверяет вычисление разности с переменными.
     */
    @Test
    void testEvalWithVariables() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));
        assertEquals(-3, e.eval("x = 10; y = 13"));
    }

    /**
     * Проверяет производную разности.
     */
    @Test
    void testDerivative() {
        Expression e = new Sub(new Variable("x"), new Number(5));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.derivative("x").print(new PrintStream(out));
        assertEquals("(1-0)", out.toString());
    }

    /**
     * Проверяет, что производная константной разности равна нулю.
     */
    @Test
    void testDerivativeConstant() {
        Expression e = new Sub(new Number(10), new Number(4));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.derivative("x").print(new PrintStream(out));
        assertEquals("(0-0)", out.toString());
    }
}