package warcardgame.players;

import warcardgame.cards.*;

import java.util.ArrayList;

public interface Player {

    public default boolean isHandEmpty(ArrayList<Card> playerHand) {
        if (playerHand.isEmpty()) {
            return true;
        }
        return false;
    }

    public default void removeCard(Card cardToRemove, ArrayList<Card> playerHand) {
        playerHand.remove(cardToRemove);
    }

    public default Card drawCard(ArrayList<Card> playerHand) {
        Card card = playerHand.get(0);
        playerHand.remove(0);
        return card;
    }

    public default void printHand(ArrayList<Card> playerHand) {
        for (Card card : playerHand) {
            System.out.println(card.getCardRank() + " of " + card.getCardSuit());
        }
    }

    void addCard(Card card);
}