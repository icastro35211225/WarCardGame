package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public interface War {
    public static final int PLAYER1 = 0;
    public static final int PLAYER2 = 1;
    public static final int PLAYER3 = 2;
    public static final int OK = 4;
    public static final int WAR = 5;

    public default void calculateScore(ArrayList<Player> players) {
        for (Player player : players) {
            player.setScore(player.getPlayerHand().size());
        }
    }

    public default void printCards(ArrayList<Card> cards) {
        for (int i = 1; i <= cards.size(); i++) {
            System.out.println("Player " + i + " plays " + cards.get(i - 1).getCardRank() + " of "
                    + cards.get(i - 1).getCardSuit());
        }
    }

    public default void printScores(ArrayList<Player> players) {
        for (int i = 1; i <= players.size(); i++) {
            System.out.println("Player " + i + " has a score of " + players.get(i - 1).getScore());
        }
    }

    public default void addWinningCard(int winningIndex, ArrayList<Player> players, ArrayList<Card> drawnCards) {
        for (Card currentCard : drawnCards) {
            players.get(winningIndex).getPlayerHand().add(currentCard);
        }
    }

    int evaluate(ArrayList<Card> cards);

    void endGame(ArrayList<Player> players);

    void war(ArrayList<Card> cards, ArrayList<Player> players);
}