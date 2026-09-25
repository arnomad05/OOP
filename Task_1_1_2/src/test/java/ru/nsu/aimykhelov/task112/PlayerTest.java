package ru.nsu.aimykhelov.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для класса {@link Player}.
 */
class PlayerTest {

    @Test
    void playerNameIsPlayer() {
        Player p = new Player();
        assertEquals("Игрок", p.getName());
    }
}