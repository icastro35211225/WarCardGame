package warcardgame.cards;

import java.util.*;

public class Deck {
    private final int SIZE = 52;
    private ArrayList<Card> cards;
    private final String[] suits = { "DIAMONDS", "CLUBS", "HEARTS", "SPADES" };
    private final String[] ranks = { "ACE", "KING", "QUEEN", "JACK", "TEN", "NINE", "EIGHT", "SEVEN", "SIX", "FIVE",
            "FOUR", "THREE", "TWO" };

    public Deck() {
        cards = new ArrayList<Card>();
        initializeDeck();
    }

    public void initializeDeck() {
        int value = 14;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                Card currentCard = new Card(suits[i], ranks[j], value);
                cards.add(currentCard);
                value--;
            }
            value = 14;
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffleDeck(int seed) {
        Random rand = new Random();
        rand.setSeed(seed);
        for (int i = 0; i < SIZE; i++) {
            int randomIndex = rand.nextInt(SIZE);
            Collections.swap(cards, i, randomIndex);
        }
    }

    public String toString() {
        String ret = "";
        for (Card card : cards) {
            ret += card.toString() + "\n";
        }
        return ret;
    }
}