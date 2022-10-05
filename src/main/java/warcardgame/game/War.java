package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface War {
    public static final int OK = 0;
    public static final int WAR = 1;
    public static final int END = -1;

    public default void dealCards(Deck deck, ArrayList<ArrayList<Card>> playerHands) {
        int i = 0;
        while (i < deck.getCards().size()) {
            if (i == 51) {
                playerHands.get(0).add(deck.getCards().get(i));
                break;
            }
            playerHands.get(0).add(deck.getCards().get(i));
            playerHands.get(1).add(deck.getCards().get(i + 1));
            if (playerHands.size() == 3) {
                playerHands.get(2).add(deck.getCards().get(i + 2));
                i += 3;
            } else {
                i += 2;
            }
        }
    }

    public default int evaluate(ArrayList<Card> cards) {
        int winningIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(winningIndex).getCardValue() < cards.get(i).getCardValue()) {
                winningIndex = i;
            }
        }
        return winningIndex;
    }

    public default int war(ArrayList<Card> cards, ArrayList<Card> warPile, ArrayList<ArrayList<Card>> playerHands) {
        PlayerPile playerHandler = new PlayerPile();
        printCards(cards);
        int winner = -1;

        if (playerHandler.findEmptyHand(playerHands)) {
            return -1;
        }

        if (checkWar(cards) == WAR) {
            warPile.addAll(playerHandler.drawCards(playerHands));
            drawnCardsToWarPile(warPile, cards);
            cards = playerHandler.drawCards(playerHands);
            System.out.println("\n***War!!***");
            winner = war(cards, warPile, playerHands);
            passWinnerCards(winner, playerHands, cards);
            return winner;
        }

        winner = evaluate(cards);
        warPile.addAll(cards);
        printRoundWinner(winner);
        passWinnerCards(winner, playerHands, warPile);
        return winner;
    }

    public default void printCards(ArrayList<Card> cards) {
        for (int i = 1; i <= cards.size(); i++) {
            System.out.println("Player " + i + " plays " + cards.get(i - 1).getCardRank() + " of "
                    + cards.get(i - 1).getCardSuit());
        }
    }

    public default void printRoundWinner(int winner) {
        System.out.println("Player " + (winner + 1) + " wins the round");
    }

    public default void drawnCardsToWarPile(ArrayList<Card> warPile, ArrayList<Card> cards) {
        for (Card currentCard : cards) {
            warPile.add(currentCard);
        }
    }

    public default int checkWar(ArrayList<Card> cards) {
        Set<Integer> values = new HashSet<Integer>();
        for (Card currentCard : cards) {
            if (values.add(currentCard.getCardValue()) == false) {
                return WAR;
            }
        }
        return OK;
    }

    public default void addToWarPile(ArrayList<Card> warPile, Card card) {
        if (!warPile.contains(card)) {
            warPile.add(card);
        }
    }

    public default void passWinnerCards(int winner, ArrayList<ArrayList<Card>> playersHands, ArrayList<Card> cards) {
        for (Card currentCard : cards) {
            if (!playersHands.get(winner).contains(currentCard)) {
                playersHands.get(winner).add(currentCard);
            }
        }
    }

    public default void checkAceTwo(ArrayList<Card> cards) {
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
}