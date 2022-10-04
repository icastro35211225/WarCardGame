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
    public static final int END = 6;

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

    public default boolean checkForTie(ArrayList<Player> players) {
        for (Player winningPlayer : players) {
            for (Player currentPlayer : players) {
                if (currentPlayer.getScore() == winningPlayer.getScore()) {
                    return true;
                }
            }
        }
        return false;
    }

    public default int war(ArrayList<Card> cards, ArrayList<Player> players, ArrayList<Card> warPile) {
        GameProcessor gameProcessor = new GameProcessor();
        printCards(cards);
        int winner;

        if (gameProcessor.emptyHands(players)) {
            return -1;
        }

        if (gameProcessor.checkWar(cards) == WAR) {
            warPile = gameProcessor.drawCards(players);
            gameProcessor.addToWarPile(warPile, cards);
            cards = gameProcessor.drawCards(players);
            System.out.println("\n***War!!***");
            winner = war(cards, players, warPile);
            passWinnerCards(winner, players, cards);
            return winner;
        }

        winner = evaluate(cards);
        gameProcessor.printRoundWinner(winner);
        passWinnerCards(winner, players, cards);
        return winner;
    }

    public default void passWinnerCards(int winner, ArrayList<Player> players, ArrayList<Card> cards) {
        for (Card currentCard : cards) {
            players.get(winner).getPlayerHand().add(currentCard);
        }
    }

    public default void endGame(ArrayList<Player> players) {
        calculateScore(players);
        int winnigIndex = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() > players.get(winnigIndex).getScore()) {
                winnigIndex = i;
            }
            if (i != 0 && players.get(i).getScore() == players.get(winnigIndex).getScore()) {
                if (checkForTie(players)) {
                    System.out.println("There's a TIE!!");
                    printScores(players);
                }
                return;
            }
        }
        System.out.println("***GAME OVER***\nPlayer " + (winnigIndex + 1) + " WINS!");
        printScores(players);
    }

    int evaluate(ArrayList<Card> cards);
}