package ru.nsu.aimykhelov.task112;

/**
 * Номиналы карт и их базовые очки.
 *
 * <p>Туз хранит значение 11; понижение до 1 выполняется в {@link Hand#getValue()},
 * поскольку решение зависит от всей руки, а не от отдельной карты.
 */
public enum Rank {
    TWO("Двойка", 2),
    THREE("Тройка", 3),
    FOUR("Четверка", 4),
    FIVE("Пятерка", 5),
    SIX("Шестерка", 6),
    SEVEN("Семерка", 7),
    EIGHT("Восьмерка", 8),
    NINE("Девятка", 9),
    TEN("Десятка", 10),
    JACK("Валет", 10),
    QUEEN("Дама", 10),
    KING("Король", 10),
    ACE("Туз", 11);

    private final String rusName;
    private final int baseValue;

    /**
     * Создаёт номинал с заданным названием и очками.
     *
     * @param rusName   название номинала на русском языке
     * @param baseValue базовое количество очков
     */
    Rank(String rusName, int baseValue) {
        this.rusName = rusName;
        this.baseValue = baseValue;
    }

    /**
     * Возвращает русскоязычное название номинала.
     *
     * @return русскоязычное название номинала
     */
    public String getRusName() {
        return rusName;
    }

    /**
     * Возвращает базовое количество очков.
     *
     * @return базовое количество очков
     */
    public int getBaseValue() {
        return baseValue;
    }

    /**
     * Проверяет, является ли номинал тузом.
     *
     * @return {@code true}, если номинал — туз
     */
    public boolean isAce() {
        return this == ACE;
    }
}