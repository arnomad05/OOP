package ru.nsu.aimykhelov.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для перечисления {@link Rank}.
 */
class RankTest {

    @Test
    void allRanksHaveNameAndValue() {
        for (Rank r : Rank.values()) {
            assertNotNull(r.getRusName());
            assertFalse(r.getRusName().isEmpty());
            assertTrue(r.getBaseValue() >= 2);
            assertTrue(r.getBaseValue() <= 11);
        }
    }

    @Test
    void numericCardsHaveExpectedValues() {
        assertEquals(2, Rank.TWO.getBaseValue());
        assertEquals(3, Rank.THREE.getBaseValue());
        assertEquals(10, Rank.TEN.getBaseValue());
    }

    @Test
    void faceCardsAreTen() {
        assertEquals(10, Rank.JACK.getBaseValue());
        assertEquals(10, Rank.QUEEN.getBaseValue());
        assertEquals(10, Rank.KING.getBaseValue());
    }

    @Test
    void aceIsEleven() {
        assertEquals(11, Rank.ACE.getBaseValue());
    }

    @Test
    void isAceOnlyForAce() {
        assertTrue(Rank.ACE.isAce());
        for (Rank r : Rank.values()) {
            if (r != Rank.ACE) {
                assertFalse(r.isAce());
            }
        }
    }

    @Test
    void thirteenRanksExist() {
        assertEquals(13, Rank.values().length);
    }
}