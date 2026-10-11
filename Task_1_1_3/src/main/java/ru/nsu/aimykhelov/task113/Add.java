package ru.nsu.aimykhelov.task113;

/**
 * Класс, представляющий операцию сложения двух выражений.
 */
public class Add extends BinaryOperation {

    /**
     * Создаёт выражение сложения.
     *
     * @param left левое слагаемое
     * @param right правое слагаемое
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Возвращает символ оператора сложения.
     *
     * @return строка "+"
     */
    @Override
    protected String getOperator() {
        return "+";
    }

    /**
     * Возвращает производную суммы: (f + g)' = f' + g'.
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return выражение, являющееся производной суммы
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    /**
     * Выполняет сложение двух чисел.
     *
     * @param left значение левого слагаемого
     * @param right значение правого слагаемого
     * @return сумма
     */
    @Override
    protected int performOperation(int left, int right) {
        return left + right;
    }
}