package ru.nsu.aimykhelov.task112;

/**
 * Одна игральная карта. Неизменяемый объект: после создания
 * масть и номинал не могут быть изменены.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Создаёт карту с заданными мастью и номиналом.
     *
     * @param suit масть карты
     * @param rank номинал карты
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Возвращает базовое количество очков карты.
     *
     * @return базовое количество очков карты
     */
    public int getBaseValue() {
        return rank.getBaseValue();
    }

    /**
     * Проверяет, является ли карта тузом.
     *
     * @return {@code true}, если карта — туз
     */
    public boolean isAce() {
        return rank.isAce();
    }

    /**
     * Возвращает номинал карты.
     *
     * @return номинал карты
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Возвращает строковое представление карты.
     *
     * @return строковое представление вида {@code "Дама Пики"}
     */
    @Override
    public String toString() {
        return rank.getRusName() + " " + suit.getRusName();
    }
}