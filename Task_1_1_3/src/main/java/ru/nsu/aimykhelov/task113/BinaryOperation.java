package ru.nsu.aimykhelov.task113;

import java.io.PrintStream;

/**
 * Абстрактный класс для бинарных операций над выражениями.
 */
public abstract class BinaryOperation extends Expression {

    /**
     * Левое подвыражение.
     */
    protected Expression left;

    /**
     * Правое подвыражение.
     */
    protected Expression right;

    /**
     * Создаёт бинарную операцию с заданными операндами.
     *
     * @param left левое подвыражение
     * @param right правое подвыражение
     */
    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Выводит выражение в формате (левый_операнд оператор правый_операнд).
     *
     * @param out поток вывода
     */
    @Override
    public void print(PrintStream out) {
        out.print("(");
        left.print(out);
        out.print(getOperator());
        right.print(out);
        out.print(")");
    }

    /**
     * Возвращает символ оператора.
     *
     * @return строковое представление оператора
     */
    protected abstract String getOperator();

    /**
     * Вычисляет значение бинарной операции.
     *
     * @param assignments строка с означиваниями переменных
     * @return результат операции
     */
    @Override
    public int eval(String assignments) {
        int leftValue = left.eval(assignments);
        int rightValue = right.eval(assignments);
        return performOperation(leftValue, rightValue);
    }

    /**
     * Выполняет арифметическую операцию над двумя числами.
     *
     * @param left значение левого операнда
     * @param right значение правого операнда
     * @return результат операции
     */
    protected abstract int performOperation(int left, int right);
}
