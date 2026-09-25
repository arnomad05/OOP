package ru.nsu.aimykhelov.task112;

/**
 * Масти игральных карт с русскоязычными названиями для вывода.
 */
public enum Suit {
    SPADES("Пики"),
    HEARTS("Червы"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы");

    private final String rusName;

    /**
     * Создаёт масть с заданным русскоязычным названием.
     *
     * @param rusName название масти на русском языке
     */
    Suit(String rusName) {
        this.rusName = rusName;
    }

    /**
     * Возвращает русскоязычное название масти.
     *
     * @return русскоязычное название масти
     */
    public String getRusName() {
        return rusName;
    }
}