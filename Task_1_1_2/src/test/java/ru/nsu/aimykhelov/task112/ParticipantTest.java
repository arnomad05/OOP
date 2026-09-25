package ru.nsu.aimykhelov.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Тесты для абстрактного класса {@link Participant}.
 * Используется конкретный наследник {@link Player}.
 */
class ParticipantTest {

    @Test
    void handIsInitialized() {
        Player p = new Player();
        assertNotNull(p.getHand());
        assertEquals(0, p.getHand().size());
    }

    @Test
    void takeCardAddsToHand() {
        Player p = new Player();
        Card c = new Card(Suit.SPADES, Rank.TEN);
        p.takeCard(c);
        assertEquals(1, p.getHand().size());
        assertSame(c, p.getHand().getCards().get(0));
    }

    @Test
    void resetHandClearsCards() {
        Player p = new Player();
        p.takeCard(new Card(Suit.SPADES, Rank.TEN));
        p.takeCard(new Card(Suit.HEARTS, Rank.FIVE));
        assertEquals(2, p.getHand().size());
        p.resetHand();
        assertEquals(0, p.getHand().size());
    }
}