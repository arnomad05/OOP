package ru.nsu.aimykhelov.task113;

/**
 * Класс, представляющий операцию умножения двух выражений.
 */
public class Mul extends BinaryOperation {

    /**
     * Создаёт выражение умножения.
     *
     * @param left левый множитель
     * @param right правый множитель
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Возвращает символ оператора умножения.
     *
     * @return строка "*"
     */
    @Override
    protected String getOperator() {
        return "*";
    }

    /**
     * Возвращает производную произведения: (f * g)' = f' * g + f * g'.
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return выражение, являющееся производной произведения
     */
    @Override
    public Expression derivative(String variable) {
        Expression leftDeriv = left.derivative(variable);
        Expression rightDeriv = right.derivative(variable);

        Expression term1 = new Mul(leftDeriv, right);
        Expression term2 = new Mul(left, rightDeriv);

        return new Add(term1, term2);
    }

    /**
     * Выполняет умножение двух чисел.
     *
     * @param left значение левого множителя
     * @param right значение правого множителя
     * @return произведение
     */
    @Override
    protected int performOperation(int left, int right) {
        return left * right;
    }
}