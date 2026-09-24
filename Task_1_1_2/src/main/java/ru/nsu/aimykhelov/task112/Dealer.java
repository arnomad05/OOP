package ru.nsu.aimykhelov.task112;

import java.util.List;

/**
 * Дилер с собственными правилами поведения: умеет показывать руку
 * со скрытой картой.
 */
class Dealer extends Participant {
    public Dealer() {
        super("Дилер");
    }

    public String describeWithHidden() {
        StringBuilder sb = new StringBuilder("[<закрытая карта>, ");
        List<Card> cards = hand.getCards();
        for (int i = 1; i < cards.size(); i++) {
            Card c = cards.get(i);
            sb.append(c.toString()).append(" (").append(c.getBaseValue()).append(")");
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}