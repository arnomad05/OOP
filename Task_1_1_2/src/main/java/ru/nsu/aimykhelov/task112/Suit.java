package ru.nsu.aimykhelov.task112;

/**
 * Масти игральных карт с русскоязычными названиями для вывода.
 */
enum Suit {
    SPADES("Пики"),
    HEARTS("Червы"),
    DIAMONDS("Бубны"),
    CLUBS("Трефы");

    private final String rusName;

    Suit(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}
