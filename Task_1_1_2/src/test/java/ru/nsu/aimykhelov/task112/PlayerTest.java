package ru.nsu.aimykhelov.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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