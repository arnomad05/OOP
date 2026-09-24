package ru.nsu.aimykhelov.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Рука участника: набор карт и правила подсчёта очков.
 */
class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card c) {
        cards.add(c);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

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

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

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