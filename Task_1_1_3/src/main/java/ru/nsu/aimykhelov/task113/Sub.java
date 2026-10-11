package ru.nsu.aimykhelov.task113;

/**
 * Класс, представляющий операцию вычитания двух выражений.
 */
public class Sub extends BinaryOperation {

    /**
     * Создаёт выражение вычитания.
     *
     * @param left уменьшаемое
     * @param right вычитаемое
     */
    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Возвращает символ оператора вычитания.
     *
     * @return строка "-"
     */
    @Override
    protected String getOperator() {
        return "-";
    }

    /**
     * Возвращает производную разности: (f - g)' = f' - g'.
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return выражение, являющееся производной разности
     */
    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    /**
     * Выполняет вычитание двух чисел.
     *
     * @param left значение уменьшаемого
     * @param right значение вычитаемого
     * @return разность
     */
    @Override
    protected int performOperation(int left, int right) {
        return left - right;
    }
}