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

    public default boolean findEmptyHand(ArrayList<ArrayList<Card>> playerHands) {
        for (ArrayList<Card> currentHand : playerHands) {
            if (isHandEmpty(currentHand)) {
                return true;
            }
        }
        return false;
    }

    public default Card drawCard(ArrayList<Card> playerHand) {
        if (!isHandEmpty(playerHand)) {
            Card card = playerHand.get(0);
            playerHand.remove(0);
            return card;
        }
        return null;
    }

    public default ArrayList<Card> drawCards(ArrayList<ArrayList<Card>> playerHands) {
        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for (ArrayList<Card> currHand : playerHands) {
            Card newCard = drawCard(currHand);
            if (newCard != null || !currHand.isEmpty())
                drawnCards.add(newCard);
        }
        return drawnCards;
    }

    public default void printHand(ArrayList<Card> playerHand) {
        for (Card card : playerHand) {
            System.out.println(card.getCardRank() + " of " + card.getCardSuit());
        }
    }

    public default void addCard(Card card, ArrayList<Card> playerHand) {
        if (!playerHand.contains(card)) {
            playerHand.add(card);
        }
    }
}