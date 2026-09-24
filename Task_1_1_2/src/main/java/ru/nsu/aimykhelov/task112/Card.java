package ru.nsu.aimykhelov.task112;

/**
 * Одна игральная карта. Неизменяемый объект: после создания
 * масть и номинал не могут быть изменены.
 */
class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getBaseValue() {
        return rank.getBaseValue();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public String toString() {
        return rank.getRusName() + " " + suit.getRusName();
    }
}
