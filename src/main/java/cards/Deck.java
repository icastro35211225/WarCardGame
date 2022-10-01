package cards;

import java.util.*;

public class Deck {
    private final int SIZE = 36;
    private ArrayList<Card> cards;
    private final String[] suits = { "DIAMONDS", "CLUBS", "HEARTS", "SPADES" };
    private final String[] ranks = { "ACE", "KING", "QUEEN", "JACK", "TEN", "NINE", "EIGHT", "SEVEN", "SIX", "FIVE",
            "FOUR", "THREE", "TWO" };

    public Deck() {
        cards = new ArrayList<Card>();
        int value = 14;
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                Card currentCard = new Card(suit, ranks[i], value);
                cards.add(currentCard);
                value--;
            }
            value = 14;
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffleDeck(long seed) {
        Random rand = new Random();
        rand.setSeed(seed);
        for (int i = 0; i < SIZE; i++) {
            int randomIndex = rand.nextInt(SIZE);
            Collections.swap(cards, i, randomIndex);
        }
    }

    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        for (Card card : cards) {
            stringBuff.append(card.toString() + "\n");
        }
        return stringBuff.toString();
    }
}