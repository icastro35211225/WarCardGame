package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

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
        GameProcessor gameProcessor = new GameProcessor();
        PlayerPile playerHandler = new PlayerPile();
        printCards(cards);
        int winner;

        if (playerHandler.findEmptyHand(playerHands)) {
            return -1;
        }

        if (gameProcessor.checkWar(cards) == WAR) {
            warPile = playerHandler.drawCards(playerHands);
            gameProcessor.addToWarPile(warPile, cards);
            cards = playerHandler.drawCards(playerHands);
            System.out.println("\n***War!!***");
            winner = war(cards, warPile, playerHands);
            gameProcessor.passWinnerCards(winner, playerHands, cards);
            return winner;
        }

        winner = evaluate(cards);
        gameProcessor.printRoundWinner(winner);
        gameProcessor.passWinnerCards(winner, playerHands, cards);
        return winner;
    }

    public default void printCards(ArrayList<Card> cards) {
        for (int i = 1; i <= cards.size(); i++) {
            System.out.println("Player " + i + " plays " + cards.get(i - 1).getCardRank() + " of "
                    + cards.get(i - 1).getCardSuit());
        }
    }

    public default void addWinningCard(int winnigIndex, ArrayList<ArrayList<Card>> playersHands,
            ArrayList<Card> drawnCards) {
        for (Card currentCard : drawnCards) {
            playersHands.get(winnigIndex).add(currentCard);
        }
    }
}