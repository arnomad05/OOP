package ru.nsu.aimykhelov.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Card}.
 */
class CardTest {

    @Test
    void baseValueDelegatesToRank() {
        Card card = new Card(Suit.SPADES, Rank.SEVEN);
        assertEquals(7, card.getBaseValue());
    }

    @Test
    void isAceWorks() {
        assertTrue(new Card(Suit.HEARTS, Rank.ACE).isAce());
        assertFalse(new Card(Suit.HEARTS, Rank.KING).isAce());
    }

    @Test
    void toStringContainsRankAndSuit() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN);
        assertEquals("Дама Пики", card.toString());
    }

    @Test
    void getRankReturnsRank() {
        Card card = new Card(Suit.CLUBS, Rank.JACK);
        assertEquals(Rank.JACK, card.getRank());
    }
}