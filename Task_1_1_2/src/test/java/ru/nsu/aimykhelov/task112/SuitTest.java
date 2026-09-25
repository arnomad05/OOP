package ru.nsu.aimykhelov.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тесты для перечисления {@link Suit}.
 */
class SuitTest {

    @Test
    void allSuitsHaveNonEmptyNames() {
        for (Suit s : Suit.values()) {
            assertNotNull(s.getRusName());
            assertFalse(s.getRusName().isEmpty());
        }
    }

    @Test
    void suitNamesAreCorrect() {
        assertEquals("Пики", Suit.SPADES.getRusName());
        assertEquals("Червы", Suit.HEARTS.getRusName());
        assertEquals("Бубны", Suit.DIAMONDS.getRusName());
        assertEquals("Трефы", Suit.CLUBS.getRusName());
    }

    @Test
    void fourSuitsExist() {
        assertEquals(4, Suit.values().length);
    }
}