package ru.nsu.aimykhelov.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link Hand}: подсчёт очков, обработка тузов,
 * определение блэкджека и перебора, формат вывода.
 */
class HandTest {

    @Test
    void addIncreasesSize() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.TEN));
        assertEquals(1, hand.size());
    }

    @Test
    void getCardsReturnsSameInstance() {
        Hand hand = new Hand();
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        hand.add(card);
        List<Card> cards = hand.getCards();
        assertEquals(1, cards.size());
        assertSame(card, cards.get(0));
    }

    @Test
    void sizeEmptyHandIsZero() {
        Hand hand = new Hand();
        assertEquals(0, hand.size());
    }

    @Test
    void getValueSimpleCards() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.TEN));
        hand.add(new Card(Suit.HEARTS, Rank.SEVEN));
        assertEquals(17, hand.getValue());
    }

    @Test
    void getValueFaceCardsAreTen() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.KING));
        hand.add(new Card(Suit.HEARTS, Rank.QUEEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.JACK));
        assertEquals(30, hand.getValue());
    }

    @Test
    void aceCountsAsElevenWhenSafe() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.HEARTS, Rank.SIX));
        assertEquals(17, hand.getValue());
    }

    @Test
    void aceDropsToOneWhenBust() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.HEARTS, Rank.TEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.FIVE));
        assertEquals(16, hand.getValue());
    }

    @Test
    void twoAcesCountAsTwelve() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(12, hand.getValue());
    }

    @Test
    void aceAndTenIsTwentyOne() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(21, hand.getValue());
    }

    @Test
    void isBlackjackWithAceAndTen() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.HEARTS, Rank.KING));
        assertTrue(hand.isBlackjack());
    }

    @Test
    void twentyOneWithThreeCardsIsNotBlackjack() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.SEVEN));
        hand.add(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        assertEquals(21, hand.getValue());
        assertFalse(hand.isBlackjack());
    }

    @Test
    void twoCardsNotTwentyOneIsNotBlackjack() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.TEN));
        hand.add(new Card(Suit.HEARTS, Rank.NINE));
        assertFalse(hand.isBlackjack());
    }

    @Test
    void isBustWhenOverTwentyOne() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.TEN));
        hand.add(new Card(Suit.HEARTS, Rank.TEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.TEN));
        assertEquals(30, hand.getValue());
        assertTrue(hand.isBust());
    }

    @Test
    void twentyOneIsNotBust() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(21, hand.getValue());
        assertFalse(hand.isBust());
    }
}