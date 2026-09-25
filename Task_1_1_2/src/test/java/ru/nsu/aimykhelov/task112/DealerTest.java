package ru.nsu.aimykhelov.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для класса {@link Dealer}.
 */
class DealerTest {

    @Test
    void dealerNameIsDealer() {
        Dealer d = new Dealer();
        assertEquals("Дилер", d.getName());
    }

    @Test
    void describeWithHiddenHidesFirstCard() {
        Dealer d = new Dealer();
        d.takeCard(new Card(Suit.SPADES, Rank.ACE));
        d.takeCard(new Card(Suit.HEARTS, Rank.SIX));
        String s = d.describeWithHidden();
        assertTrue(s.contains("<закрытая карта>"));
        assertTrue(s.contains("Шестерка Червы"));
    }

    @Test
    void describeWithHiddenDoesNotRevealFirstCard() {
        Dealer d = new Dealer();
        d.takeCard(new Card(Suit.SPADES, Rank.ACE));
        d.takeCard(new Card(Suit.HEARTS, Rank.SIX));
        String s = d.describeWithHidden();
        assertTrue(!s.contains("Туз Пики"));
    }

    @Test
    void describeWithHiddenStartsWithBracket() {
        Dealer d = new Dealer();
        d.takeCard(new Card(Suit.SPADES, Rank.TEN));
        d.takeCard(new Card(Suit.HEARTS, Rank.SEVEN));
        assertTrue(d.describeWithHidden().startsWith("["));
    }
}