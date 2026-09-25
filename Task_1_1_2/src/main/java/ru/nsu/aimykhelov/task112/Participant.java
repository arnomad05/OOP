package ru.nsu.aimykhelov.task112;

/**
 * Общая абстракция участника игры: имя и рука.
 * Наследники — {@link Player} и {@link Dealer}.
 */
public abstract class Participant {
    protected final String name;
    protected final Hand hand = new Hand();

    /**
     * Создаёт участника с заданным именем.
     *
     * @param name имя участника
     */
    protected Participant(String name) {
        this.name = name;
    }

    /**
     * Возвращает руку участника.
     *
     * @return рука участника
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Возвращает имя участника.
     *
     * @return имя участника
     */
    public String getName() {
        return name;
    }

    /**
     * Очищает руку перед началом нового раунда.
     */
    public void resetHand() {
        hand.getCards().clear();
    }

    /**
     * Добавляет карту в руку участника.
     *
     * @param c карта, которую берёт участник
     */
    public void takeCard(Card c) {
        hand.add(c);
    }
}