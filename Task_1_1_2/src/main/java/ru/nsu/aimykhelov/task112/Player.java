package ru.nsu.aimykhelov.task112;

/**
 * Игрок-пользователь. Вся логика хода находится в {@link BlackjackGame}.
 */
public class Player extends Participant {

    /**
     * Создаёт игрока с именем «Игрок».
     */
    public Player() {
        super("Игрок");
    }
}