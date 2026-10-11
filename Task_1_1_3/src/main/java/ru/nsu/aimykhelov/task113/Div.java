package ru.nsu.aimykhelov.task113;

/**
 * Класс, представляющий операцию деления двух выражений.
 */
public class Div extends BinaryOperation {

    /**
     * Создаёт выражение деления.
     *
     * @param left делимое
     * @param right делитель
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Возвращает символ оператора деления.
     *
     * @return строка "/"
     */
    @Override
    protected String getOperator() {
        return "/";
    }

    /**
     * Возвращает производную частного: (f / g)' = (f' * g - f * g') / g^2.
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return выражение, являющееся производной частного
     */
    @Override
    public Expression derivative(String variable) {
        Expression leftDeriv = left.derivative(variable);
        Expression rightDeriv = right.derivative(variable);

        Expression numerator1 = new Mul(leftDeriv, right);
        Expression numerator2 = new Mul(left, rightDeriv);
        Expression numerator = new Sub(numerator1, numerator2);

        Expression denominator = new Mul(right, right);

        return new Div(numerator, denominator);
    }

    /**
     * Выполняет деление двух чисел.
     *
     * @param left значение делимого
     * @param right значение делителя
     * @return частное
     * @throws ArithmeticException если делитель равен нулю
     */
    @Override
    protected int performOperation(int left, int right) {
        if (right == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left / right;
    }
}
