package ru.nsu.aimykhelov.task112;

import java.util.Scanner;

/**
 * Основной класс игры: управляет раундами, вводом-выводом
 * и подсчётом побед игрока и дилера.
 */
public class BlackjackGame {
    private final Scanner scanner = new Scanner(System.in);
    private final Deck deck;
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();
    private int playerWins = 0;
    private int dealerWins = 0;

    /**
     * Создаёт игру с заданным числом колод.
     *
     * @param deckCount количество колод в игре
     */
    public BlackjackGame(int deckCount) {
        this.deck = new Deck(deckCount);
    }

    /**
     * Запускает основной цикл игры: раунды до отказа пользователя.
     */
    public void start() {
        System.out.println("Добро пожаловать в Блэкджек!");
        int round = 1;
        while (true) {
            System.out.println();
            System.out.println("Раунд " + round);
            playRound();
            System.out.printf("Счет %d:%d в вашу пользу.%n", playerWins, dealerWins);

            System.out.println("Хотите сыграть еще раунд? (1 - да, 0 - нет)");
            if (!readYesNo()) {
                break;
            }
            round++;
        }
        System.out.println("Спасибо за игру!");
    }

    /**
     * Проводит один раунд: раздачу, ход игрока, ход дилера
     * и определение победителя.
     */
    private void playRound() {
        player.resetHand();
        dealer.resetHand();

        System.out.println("Дилер раздал карты");
        player.takeCard(deck.draw());
        dealer.takeCard(deck.draw());
        player.takeCard(deck.draw());
        dealer.takeCard(deck.draw());

        printPlayerHand();
        System.out.println("Карты дилера: " + dealer.describeWithHidden());

        boolean playerBlackJack = player.getHand().isBlackjack();
        boolean dealerBlackJack = dealer.getHand().isBlackjack();

        if (playerBlackJack || dealerBlackJack) {
            printPlayerHand();
            System.out.println("Карты дилера: " + dealer.getHand().describe());
            if (playerBlackJack && dealerBlackJack) {
                System.out.println("У обоих блэкджек! Ничья.");
            } else if (playerBlackJack) {
                playerWins++;
                System.out.println("У вас блэкджек! Вы выиграли раунд!");
            } else {
                dealerWins++;
                System.out.println("У дилера блэкджек. Вы проиграли раунд.");
            }
            return;
        }

        playerTurn();
        if (player.getHand().isBust()) {
            printPlayerHand();
            dealerWins++;
            System.out.println("Перебор! Вы проиграли раунд.");
            return;
        }

        dealerTurn();

        int pv = player.getHand().getValue();
        int dv = dealer.getHand().getValue();

        if (dealer.getHand().isBust()) {
            playerWins++;
            System.out.println("У дилера перебор! Вы выиграли раунд!");
        } else if (pv > dv) {
            playerWins++;
            System.out.println("Вы выиграли раунд!");
        } else if (pv < dv) {
            dealerWins++;
            System.out.println("Вы проиграли раунд.");
        } else {
            System.out.println("Ничья.");
        }
    }

    /**
     * Интерактивный ход игрока: взятие карт до остановки,
     * перебора или достижения 21.
     */
    private void playerTurn() {
        System.out.println();
        System.out.println("Ваш ход");
        System.out.println("-------");
        while (true) {
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться .");
            int choice = readInt();
            if (choice == 1) {
                Card c = deck.draw();
                player.takeCard(c);
                System.out.println("Вы открыли карту " + c.toString()
                        + " (" + c.getBaseValue() + ")");
                printPlayerHand();
                System.out.println("Карты дилера: " + dealer.describeWithHidden());
                if (player.getHand().isBust() || player.getHand().getValue() == 21) {
                    return;
                }
            } else if (choice == 0) {
                return;
            } else {
                System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    /**
     * Ход дилера: открытие закрытой карты и добор до 17.
     * Каждая взятая карта выводится отдельно.
     */
    private void dealerTurn() {
        System.out.println("Ход дилера");
        System.out.println("-------");

        Card hidden = dealer.getHand().getCards().get(0);
        System.out.println("Дилер открывает закрытую карту " + hidden.toString()
                + " (" + hidden.getBaseValue() + ")");
        printPlayerHand();
        System.out.println("Карты дилера: " + dealer.getHand().describe());

        while (dealer.getHand().getValue() < 17) {
            Card c = deck.draw();
            dealer.takeCard(c);
            System.out.println("Дилер открывает карту " + c.toString()
                    + " (" + c.getBaseValue() + ")");
            printPlayerHand();
            System.out.println("Карты дилера: " + dealer.getHand().describe());
        }
    }

    /**
     * Печатает руку игрока с суммой очков.
     */
    private void printPlayerHand() {
        System.out.println("Ваши карты: " + player.getHand().describe());
    }

    /**
     * Читает целое число из консоли, повторяя запрос при ошибке.
     *
     * @return введённое целое число
     */
    private int readInt() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введите число 1 или 0.");
            }
        }
    }

    /**
     * Читает ответ «да/нет» (1/0) из консоли.
     *
     * @return {@code true} для 1, {@code false} для 0
     */
    private boolean readYesNo() {
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equals("1")) {
                return true;
            }
            if (line.equals("0")) {
                return false;
            }
            System.out.println("Введите 1 или 0.");
        }
    }

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        new BlackjackGame(1).start();
    }
}