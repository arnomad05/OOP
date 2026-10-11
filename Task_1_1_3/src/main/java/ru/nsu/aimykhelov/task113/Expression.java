package ru.nsu.aimykhelov.task113;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный класс, представляющий математическое выражение.
 * Является базовым классом для всех выражений в иерархии.
 */
public abstract class Expression {

    /**
     * Выводит выражение в стандартный поток вывода.
     */
    public void print() {
        print(System.out);
    }

    /**
     * Выводит выражение в заданный поток вывода.
     *
     * @param out поток вывода
     */
    public abstract void print(PrintStream out);

    /**
     * Выполняет символьное дифференцирование выражения по заданной переменной.
     *
     * @param variable имя переменной, по которой производится дифференцирование
     * @return новое выражение, являющееся производной
     */
    public abstract Expression derivative(String variable);

    /**
     * Вычисляет значение выражения при заданном означивании переменных.
     *
     * @param assignments строка с означиваниями переменных в формате "x = 10; y = 13"
     * @return целочисленное значение выражения
     */
    public abstract int eval(String assignments);

    /**
     * Вспомогательный метод для парсинга строки с означиваниями переменных.
     *
     * @param assignments строка с означиваниями переменных
     * @return карта соответствия имён переменных и их значений
     */
    protected Map<String, Integer> parseAssignments(String assignments) {
        Map<String, Integer> values = new HashMap<>();
        if (assignments == null || assignments.trim().isEmpty()) {
            return values;
        }

        String[] pairs = assignments.split(";");
        for (String pair : pairs) {
            String[] parts = pair.trim().split("=");
            if (parts.length == 2) {
                String varName = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                values.put(varName, value);
            }
        }
        return values;
    }

    /**
     * Статический метод для создания выражения из строки.
     * Каждое выражение, кроме переменных и констант, пишется в скобках.
     *
     * @param str строка с выражением
     * @return построенное выражение
     */
    public static Expression parse(String str) {
        str = str.trim();

        if (str.startsWith("(") && str.endsWith(")")) {
            str = str.substring(1, str.length() - 1);
        }

        try {
            int num = Integer.parseInt(str);
            return new Number(num);
        } catch (NumberFormatException e) {
            // Не число
        }

        int brackets = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                brackets++;
            } else if (c == ')') {
                brackets--;
            } else if (brackets == 0) {
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    String left = str.substring(0, i);
                    String right = str.substring(i + 1);
                    Expression leftExpr = parse(left);
                    Expression rightExpr = parse(right);

                    switch (c) {
                        case '+':
                            return new Add(leftExpr, rightExpr);
                        case '-':
                            return new Sub(leftExpr, rightExpr);
                        case '*':
                            return new Mul(leftExpr, rightExpr);
                        case '/':
                            return new Div(leftExpr, rightExpr);
                        default:
                            throw new IllegalStateException(
                                    "Unexpected operator: " + c);
                    }
                }
            }
        }

        return new Variable(str);
    }
}
