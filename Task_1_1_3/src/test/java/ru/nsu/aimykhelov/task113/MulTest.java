package ru.nsu.aimykhelov.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Mul}.
 */
class MulTest {

    /**
     * Проверяет печать произведения.
     */
    @Test
    void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Mul(new Number(2), new Variable("x")).print(new PrintStream(out));
        assertEquals("(2*x)", out.toString());
    }

    /**
     * Проверяет вычисление произведения.
     */
    @Test
    void testEval() {
        assertEquals(20, new Mul(new Number(2), new Variable("x")).eval("x = 10"));
    }

    /**
     * Проверяет производную произведения по правилу Лейбница.
     */
    @Test
    void testDerivative() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.derivative("x").print(new PrintStream(out));
        assertEquals("((0*x)+(2*1))", out.toString());
    }

    /**
     * Проверяет производную произведения двух переменных.
     */
    @Test
    void testDerivativeTwoVariables() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.derivative("x").print(new PrintStream(out));
        assertEquals("((1*y)+(x*0))", out.toString());
    }

    /**
     * Проверяет значение производной при означивании.
     */
    @Test
    void testDerivativeEval() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        Expression d = e.derivative("x");
        assertEquals(13, d.eval("x = 10; y = 13"));
    }
}