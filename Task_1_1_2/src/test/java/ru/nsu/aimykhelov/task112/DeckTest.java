package ru.nsu.aimykhelov.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Deck}.
 */
class DeckTest {

    @Test
    void singleDeckHas52Cards() {
        Deck deck = new Deck(1);
        assertEquals(52, deck.size());
    }

    @Test
    void twoDecksHave104Cards() {
        Deck deck = new Deck(2);
        assertEquals(104, deck.size());
    }

    @Test
    void zeroDecksDefaultsToOne() {
        Deck deck = new Deck(0);
        assertEquals(52, deck.size());
    }

    @Test
    void negativeDecksDefaultsToOne() {
        Deck deck = new Deck(-5);
        assertEquals(52, deck.size());
    }

    @Test
    void drawReducesSize() {
        Deck deck = new Deck(1);
        deck.draw();
        assertEquals(51, deck.size());
    }

    @Test
    void drawReturnsCard() {
        Deck deck = new Deck(1);
        assertNotNull(deck.draw());
    }

    @Test
    void drawFromEmptyDeckThrows() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThrows(IllegalStateException.class, deck::draw);
    }

    @Test
    void deckBecomesEmptyAfterAllCardsDrawn() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertEquals(0, deck.size());
    }
}