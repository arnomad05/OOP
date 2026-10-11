package ru.nsu.aimykhelov.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для статического метода {@link Expression#parse(String)} и
 * вспомогательного {@link Expression#parseAssignments(String)}.
 */
class ExpressionTest {

    /**
     * Проверяет разбор константы.
     */
    @Test
    void testParseNumber() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("42").print(new PrintStream(out));
        assertEquals("42", out.toString());
    }

    /**
     * Проверяет разбор отрицательной константы.
     */
    @Test
    void testParseNegativeNumber() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("-7").print(new PrintStream(out));
        assertEquals("-7", out.toString());
    }

    /**
     * Проверяет разбор переменной.
     */
    @Test
    void testParseVariable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("x").print(new PrintStream(out));
        assertEquals("x", out.toString());
    }

    /**
     * Проверяет разбор многобуквенной переменной.
     */
    @Test
    void testParseMultiLetterVariable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("alpha").print(new PrintStream(out));
        assertEquals("alpha", out.toString());
    }

    /**
     * Проверяет разбор суммы.
     */
    @Test
    void testParseAdd() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("(3+5)").print(new PrintStream(out));
        assertEquals("(3+5)", out.toString());
    }

    /**
     * Проверяет разбор разности.
     */
    @Test
    void testParseSub() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("(10-4)").print(new PrintStream(out));
        assertEquals("(10-4)", out.toString());
    }

    /**
     * Проверяет разбор произведения.
     */
    @Test
    void testParseMul() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("(2*x)").print(new PrintStream(out));
        assertEquals("(2*x)", out.toString());
    }

    /**
     * Проверяет разбор частного.
     */
    @Test
    void testParseDiv() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("(x/y)").print(new PrintStream(out));
        assertEquals("(x/y)", out.toString());
    }

    /**
     * Проверяет разбор вложенного выражения.
     */
    @Test
    void testParseNested() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("(3+(2*x))").print(new PrintStream(out));
        assertEquals("(3+(2*x))", out.toString());
    }

    /**
     * Проверяет, что разобранное выражение вычисляется корректно.
     */
    @Test
    void testParseAndEval() {
        Expression e = Expression.parse("(3+(2*x))");
        assertEquals(23, e.eval("x = 10"));
    }

    /**
     * Проверяет, что разобранное выражение дифференцируется корректно.
     */
    @Test
    void testParseAndDerivative() {
        Expression e = Expression.parse("(3+(2*x))");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.derivative("x").print(new PrintStream(out));
        assertEquals("(0+((0*x)+(2*1)))", out.toString());
    }

    /**
     * Проверяет разбор с пробелами по краям.
     */
    @Test
    void testParseWithWhitespace() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Expression.parse("  (3+5)  ").print(new PrintStream(out));
        assertEquals("(3+5)", out.toString());
    }

    /**
     * Проверяет разбор глубоко вложенного выражения.
     */
    @Test
    void testParseDeeplyNested() {
        Expression e = Expression.parse("((x*y)+(a/b))");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.print(new PrintStream(out));
        assertEquals("((x*y)+(a/b))", out.toString());
    }

    /**
     * Проверяет, что parseAssignments корректно разбирает несколько переменных.
     */
    @Test
    void testParseAssignmentsMultiple() {
        Expression e = new Add(new Variable("x"), new Variable("y"));
        assertEquals(23, e.eval("x = 10; y = 13"));
    }

    /**
     * Проверяет, что parseAssignments игнорирует пустые пары.
     */
    @Test
    void testParseAssignmentsWithExtraSpaces() {
        Expression e = new Variable("x");
        assertEquals(10, e.eval("  x   =   10  "));
    }

    /**
     * Проверяет, что parseAssignments корректно обрабатывает null.
     */
    @Test
    void testEvalWithNullAssignments() {
        assertThrows(IllegalArgumentException.class,
                () -> new Variable("x").eval(null));
    }

    /**
     * Проверяет, что parseAssignments корректно обрабатывает несколько переменных с пробелами.
     */
    @Test
    void testParseAssignmentsComplex() {
        Expression e = new Add(new Variable("alpha"), new Variable("beta"));
        assertEquals(30, e.eval("alpha = 10; beta = 20"));
    }
}