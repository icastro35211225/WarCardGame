package warcardgame.game;

import java.util.*;
import warcardgame.cards.*;
import warcardgame.players.Player;

public class GameProcessor {
    private static final int PLAYER1 = 0;
    private static final int PLAYER2 = 1;
    private static final int PLAYER3 = 2;
    private static final int OK = 4;
    private static final int WAR = 5;

    public void addToWarPile(ArrayList<Card> warPile, Card card) {
        if (!warPile.contains(card)) {
            warPile.add(card);
        }
    }

    // ADD TO PLAYER INTERFACE
    public void dealCards(Deck deck, ArrayList<Player> players) {
        int i = 0;
        while (i < deck.getCards().size()) {
            if (i == 51) {
                players.get(PLAYER1).getPlayerHand().add(deck.getCards().get(i));
                break;
            }
            players.get(PLAYER1).getPlayerHand().add(deck.getCards().get(i));
            players.get(PLAYER2).getPlayerHand().add(deck.getCards().get(i + 1));
            if (players.size() == 3) {
                players.get(PLAYER3).getPlayerHand().add(deck.getCards().get(i + 2));
                i += 3;
            } else {
                i += 2;
            }
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

    public void resetCardValues(ArrayList<Player> players) {
        for (Player player : players) {
            for (Card card : player.getPlayerHand()) {
                if (card.getCardValue() == 15) {
                    card.setCardValue(2);
                }
            }
        }
    }

    public boolean emptyHands(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerHand().isEmpty()) {
                return true;
            }
        }
        return false;
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