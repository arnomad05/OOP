package ru.nsu.aimykhelov.task112;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса {@link BlackjackGame}. Подменяет стандартные
 * потоки ввода-вывода, чтобы эмулировать действия пользователя
 * и перехватывать вывод программы.
 */
class BlackjackGameTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
        originalOut = System.out;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void constructorWorks() {
        BlackjackGame game = new BlackjackGame(1);
        assertNotNull(game);
    }

    @Test
    void oneRoundWithImmediateStop() {
        provideInput("0\n0\n");
        BlackjackGame game = new BlackjackGame(1);
        game.start();
        String out = capturedOut.toString(StandardCharsets.UTF_8);
        assertTrue(out.contains("Раунд 1"));
        assertTrue(out.contains("Счет"));
    }

    @Test
    void gameAsksForAnotherRound() {
        provideInput("0\n0\n");
        new BlackjackGame(1).start();
        String out = capturedOut.toString(StandardCharsets.UTF_8);
        assertTrue(out.contains("Хотите сыграть еще раунд?"));
    }

    @Test
    void takeCardPrintsOpenedCard() {
        provideInput("1\n0\n0\n");
        new BlackjackGame(1).start();
        String out = capturedOut.toString(StandardCharsets.UTF_8);
        assertTrue(out.contains("Вы открыли карту"));
    }

    @Test
    void invalidInputDoesNotCrash() {
        provideInput("abc\n0\n0\n");
        new BlackjackGame(1).start();
        String out = capturedOut.toString(StandardCharsets.UTF_8);
        assertTrue(out.contains("Введите число"));
    }

    @Test
    void decliningNextRoundEndsGame() {
        provideInput("0\n0\n");
        new BlackjackGame(1).start();
        String out = capturedOut.toString(StandardCharsets.UTF_8);
        assertFalse(out.contains("Раунд 2"));
    }

    @Test
    void acceptingNextRoundStartsRoundTwo() {
        provideInput("0\n1\n0\n0\n");
        new BlackjackGame(1).start();
        String out = capturedOut.toString(StandardCharsets.UTF_8);
        assertTrue(out.contains("Раунд 2"));
    }
}