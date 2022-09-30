package game;

import java.util.*;
import cards.*;

public class GameProcessor {
    private Card player1Card = null, player2Card = null, player3Card = null;

    /*
     * for(int i = 0; i < 0; i++){
     * cardsOnTable[i] = -1;
     * }
     */

    public Card evaluteCards(ArrayList<Card> cards) {
        player1Card = cards.get(0);
        player2Card = cards.get(1);
        if (cards.size() == 3) {
            player3Card = cards.get(2);
        }
        if (containsAce(cards) && containsSix(cards)) {

        }
        Card winningCard = player1Card;
        if (winningCard.getCardValue() < player2Card.getCardValue()) {
            winningCard = player2Card;
        }
        if (cards.size() == 3) {
            if (winningCard.getCardValue() < player3Card.getCardValue()) {
                winningCard = player3Card;
            }
        }

        return winningCard;
    }

    public boolean containsAce(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getCardRank().equals("ACE")) {
                return true;
            }
        }
        return false;
    }

    public boolean containsSix(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getCardRank().equals("SIX")) {
                return true;
            }
        }
        return false;
    }
}