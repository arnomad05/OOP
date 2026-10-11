package ru.nsu.aimykhelov.task113;

import java.io.PrintStream;
import java.util.Map;

/**
 * Класс, представляющий переменную.
 */
public class Variable extends Expression {

    /**
     * Имя переменной.
     */
    private String name;

    /**
     * Создаёт переменную с заданным именем.
     *
     * @param name имя переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Выводит имя переменной в заданный поток.
     *
     * @param out поток вывода
     */
    @Override
    public void print(PrintStream out) {
        out.print(name);
    }

    /**
     * Возвращает производную переменной по заданной переменной.
     * Если имена совпадают, возвращает Number(1), иначе Number(0).
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return выражение Number(1) или Number(0)
     */
    @Override
    public Expression derivative(String variable) {
        if (name.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Возвращает значение переменной из заданного означивания.
     *
     * @param assignments строка с означиваниями переменных
     * @return значение переменной
     * @throws IllegalArgumentException если переменная не означена
     */
    @Override
    public int eval(String assignments) {
        Map<String, Integer> values = parseAssignments(assignments);
        if (!values.containsKey(name)) {
            throw new IllegalArgumentException("Variable " + name + " is not assigned");
        }
        return values.get(name);
    }
}
