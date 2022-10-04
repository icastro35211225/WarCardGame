package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public interface War {

    public default void calculateScore(ArrayList<Player> players) {
        int player1HandCount = players.get(0).getPlayerHand().size();
        int player2HandCount = players.get(1).getPlayerHand().size();
        players.get(0).setScore(player1HandCount);
        players.get(1).setScore(player2HandCount);
    }

    public default void printCards(ArrayList<Card> cards) {
        String player1Rank = cards.get(0).getCardRank();
        String player1Suit = cards.get(0).getCardSuit();
        String player2Rank = cards.get(1).getCardRank();
        String player2Suit = cards.get(1).getCardSuit();
        System.out.println("Player 1 plays " + player1Rank + " of " + player1Suit);
        System.out.println("Player 2 plays " + player2Rank + " of " + player2Suit);
        if (cards.size() == 3) {
            String player3Rank = cards.get(2).getCardRank();
            String player3Suit = cards.get(2).getCardSuit();
            System.out.println("Player 3 plays " + player3Rank + " of " + player3Suit);
        }
    }

    public default void printScores(ArrayList<Player> players) {
        System.out.println("Player 1 has a score of " + players.get(0).getScore());
        System.out.println("Player 2 has a score of " + players.get(1).getScore());
        if (players.size() == 3) {
            System.out.println("Player 3 has a score of " + players.get(1).getScore());
        }
    }

    int evaluate(ArrayList<Card> cards);

    void endGame(ArrayList<Player> players);

    void war(ArrayList<Card> cards);
}