package warcardgame.game;

import java.util.*;
import warcardgame.cards.*;

public class GameProcessor {
    private static final int OK = 4;
    private static final int WAR = 5;

    public void addToWarPile(ArrayList<Card> warPile, Card card) {
        if (!warPile.contains(card)) {
            warPile.add(card);
        }
    }

    public void passWinnerCards(int winner, ArrayList<ArrayList<Card>> playersHands, ArrayList<Card> cards) {
        for (Card currentCard : cards) {
            playersHands.get(winner).add(currentCard);
        }
    }

    public void checkAceTwo(ArrayList<Card> cards) {
        boolean isThereAce = false;
        boolean isThereTwo = false;
        int twoIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardValue() == 14) {
                isThereAce = true;
            }
            if (cards.get(i).getCardValue() == 2) {
                isThereTwo = true;
                twoIndex = i;
            }
        }
        if (isThereAce && isThereTwo) {
            cards.get(twoIndex).setCardValue(15);
        }
    }

    public int checkWar(ArrayList<Card> cards) {
        Set<Integer> values = new HashSet<Integer>();
        for (Card currentCard : cards) {
            if (values.add(currentCard.getCardValue()) == false) {
                return WAR;
            }
        }
        return OK;
    }

    public void addToWarPile(ArrayList<Card> warPile, ArrayList<Card> cards) {
        for (Card currentCard : cards) {
            warPile.add(currentCard);
        }
    }

    public void printRoundWinner(int winner) {
        System.out.println("Player " + (winner + 1) + " wins the round");
    }
}