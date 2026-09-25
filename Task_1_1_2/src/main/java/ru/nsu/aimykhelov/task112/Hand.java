package ru.nsu.aimykhelov.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Рука участника: набор карт и правила подсчёта очков.
 */
public class Hand {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Добавляет карту в руку.
     *
     * @param c карта для добавления
     */
    public void add(Card c) {
        cards.add(c);
    }

    /**
     * Возвращает список карт в руке.
     *
     * @return список карт в руке
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Возвращает количество карт в руке.
     *
     * @return количество карт в руке
     */
    public int size() {
        return cards.size();
    }

    /**
     * Считает сумму очков руки с корректной обработкой тузов.
     * Все тузы сначала считаются по 11; пока сумма превышает 21,
     * каждый туз понижается до 1.
     *
     * @return сумма очков руки
     */
    public int getValue() {
        int sum = 0;
        int aces = 0;
        for (Card c : cards) {
            sum += c.getBaseValue();
            if (c.isAce()) {
                aces++;
            }
        }
        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }
        return sum;
    }

    /**
     * Проверяет, является ли рука блэкджеком.
     * Блэкджек — ровно две карты с суммой 21.
     *
     * @return {@code true}, если у руки блэкджек
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    /**
     * Проверяет, превысила ли сумма очков 21.
     *
     * @return {@code true}, если сумма очков превышает 21
     */
    public boolean isBust() {
        return getValue() > 21;
    }

    /**
     * Возвращает строковое представление руки с суммой очков.
     *
     * @return строковое представление руки с суммой очков
     */
    public String describe() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            sb.append(c.toString()).append(" (").append(c.getBaseValue()).append(")");
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] > ").append(getValue());
        return sb.toString();
    }
}