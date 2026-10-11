package ru.nsu.aimykhelov.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Add}.
 */
class AddTest {

    /**
     * Проверяет печать суммы двух констант.
     */
    @Test
    void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Add(new Number(3), new Number(5)).print(new PrintStream(out));
        assertEquals("(3+5)", out.toString());
    }

    /**
     * Проверяет печать вложенной суммы.
     */
    @Test
    void testPrintNested() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e.print(new PrintStream(out));
        assertEquals("(3+(2*x))", out.toString());
    }

    /**
     * Проверяет вычисление суммы.
     */
    @Test
    void testEval() {
        Expression e = new Add(new Number(3), new Number(5));
        assertEquals(8, e.eval(""));
    }

    /**
     * Проверяет вычисление суммы с переменными.
     */
    @Test
    void testEvalWithVariables() {
        Expression e = new Add(new Variable("x"), new Variable("y"));
        assertEquals(23, e.eval("x = 10; y = 13"));
    }

    /**
     * Проверяет производную суммы.
     */
    @Test
    void testDerivative() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.derivative("x").print(new PrintStream(out));
        assertEquals("(0+((0*x)+(2*1)))", out.toString());
    }

    /**
     * Проверяет, что исходное выражение не меняется после дифференцирования.
     */
    @Test
    void testDerivativeDoesNotMutateOriginal() {
        Expression e = new Add(new Number(3), new Variable("x"));
        e.derivative("x");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.print(new PrintStream(out));
        assertEquals("(3+x)", out.toString());
    }
}