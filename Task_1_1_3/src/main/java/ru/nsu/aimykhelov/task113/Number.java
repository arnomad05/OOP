package ru.nsu.aimykhelov.task113;

import java.io.PrintStream;

/**
 * Класс, представляющий константу (целое число).
 */
public class Number extends Expression {

    /**
     * Значение константы.
     */
    private int value;

    /**
     * Создаёт константу с заданным значением.
     *
     * @param value значение константы
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * Выводит значение константы в заданный поток.
     *
     * @param out поток вывода
     */
    @Override
    public void print(PrintStream out) {
        out.print(value);
    }

    /**
     * Возвращает производную константы, которая всегда равна нулю.
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return выражение Number(0)
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    /**
     * Возвращает значение константы.
     *
     * @param assignments строка с означиваниями переменных (игнорируется)
     * @return значение константы
     */
    @Override
    public int eval(String assignments) {
        return value;
    }
}
