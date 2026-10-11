package ru.nsu.aimykhelov.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Variable}.
 */
class VariableTest {

    /**
     * Проверяет вывод имени переменной.
     */
    @Test
    void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Variable("x").print(new PrintStream(out));
        assertEquals("x", out.toString());
    }

    /**
     * Проверяет вывод многобуквенного имени переменной.
     */
    @Test
    void testPrintMultiLetterName() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Variable("alpha").print(new PrintStream(out));
        assertEquals("alpha", out.toString());
    }

    /**
     * Проверяет, что производная по той же переменной равна единице.
     */
    @Test
    void testDerivativeSameVariable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Variable("x").derivative("x").print(new PrintStream(out));
        assertEquals("1", out.toString());
    }

    /**
     * Проверяет, что производная по другой переменной равна нулю.
     */
    @Test
    void testDerivativeDifferentVariable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Variable("x").derivative("y").print(new PrintStream(out));
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет вычисление значения переменной.
     */
    @Test
    void testEval() {
        assertEquals(10, new Variable("x").eval("x = 10"));
        assertEquals(13, new Variable("y").eval("x = 10; y = 13"));
    }

    /**
     * Проверяет вычисление многобуквенной переменной.
     */
    @Test
    void testEvalMultiLetterName() {
        assertEquals(5, new Variable("alpha").eval("alpha = 5"));
    }

    /**
     * Проверяет, что при отсутствии означивания бросается исключение.
     */
    @Test
    void testEvalUnassignedThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Variable("x").eval("y = 10"));
    }

    /**
     * Проверяет, что при пустой строке бросается исключение.
     */
    @Test
    void testEvalEmptyAssignmentsThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Variable("x").eval(""));
    }
}