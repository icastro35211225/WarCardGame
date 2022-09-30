package cards;

import java.util.ArrayList;

public class Hand extends Deck {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isHandEmpty() {
        if (cards.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean removeCard(Card cardToBeRemoved) {
        if (cards.contains(cardToBeRemoved)) {
            try {
                cards.remove(cardToBeRemoved);
                return true;
            } catch (Exception e) {
                System.err.print(e);
                return false;
            }
        }
        return false;
    }

    public boolean addCard(Card cardToBeAdded) {
        if (!cards.contains(cardToBeAdded)) {
            cards.add(cardToBeAdded);
            return true;
        }
        System.out.print("ERROR! Duplicate card");
        return false;
    }

    public void printHand() {
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }

    public void addToPlayerHand(Card card){
        cards.add(card);
    }
}