package ru.nsu.aimykhelov.task112;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Колода карт. Может состоять из одной или нескольких стандартных
 * колод по 52 карты. Карты тасуются при создании.
 */
public class Deck {
    private final Deque<Card> cards = new ArrayDeque<>();

    /**
     * Создаёт и тасует колоду из заданного числа стандартных колод.
     * Если число колод меньше единицы, используется одна колода.
     *
     * @param deckCount количество колод (минимум 1)
     */
    public Deck(int deckCount) {
        if (deckCount < 1) {
            deckCount = 1;
        }
        List<Card> all = new ArrayList<>();
        for (int d = 0; d < deckCount; d++) {
            for (Suit s : Suit.values()) {
                for (Rank r : Rank.values()) {
                    all.add(new Card(s, r));
                }
            }
        }
        Collections.shuffle(all);
        cards.addAll(all);
    }

    /**
     * Извлекает верхнюю карту колоды.
     *
     * @return верхняя карта
     * @throws IllegalStateException если колода пуста
     */
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Колода пуста");
        }
        return cards.pollFirst();
    }

    /**
     * Возвращает количество оставшихся карт.
     *
     * @return количество оставшихся карт
     */
    public int size() {
        return cards.size();
    }
}