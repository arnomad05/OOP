package ru.nsu.aimykhelov.task112;

/**
 * Общая абстракция участника игры: имя и рука.
 * Наследники — {@link Player} и {@link Dealer}.
 */
abstract class Participant {
    protected final String name;
    protected final Hand hand = new Hand();

    protected Participant(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void resetHand() {
        hand.getCards().clear();
    }

    public void takeCard(Card c) {
        hand.add(c);
    }
}