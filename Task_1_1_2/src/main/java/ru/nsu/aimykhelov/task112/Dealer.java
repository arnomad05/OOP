package ru.nsu.aimykhelov.task112;

import java.util.List;

/**
 * Дилер с собственными правилами поведения: умеет показывать руку
 * со скрытой картой.
 */
public class Dealer extends Participant {

    /**
     * Создаёт дилера с именем «Дилер».
     */
    public Dealer() {
        super("Дилер");
    }

    /**
     * Формирует представление руки дилера до открытия закрытой карты:
     * первая карта показывается как {@code <закрытая карта>},
     * остальные — с номиналом и мастью.
     *
     * @return строковое представление руки со скрытой картой
     */
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