package game;

import cards.*;
import players.*;

import java.util.ArrayList;

//use interfaces to implement diff variations

// version ehre 2 players play until:
// 1) one player has won all of the cards
// 2) a max number of rounds have been played
public interface War {
    public Deck deck = new Deck();
    public Player player3 = new Player();
    public GameProcessor gameProcessor = new GameProcessor();

    public default void calculateScore(ArrayList<Player> players) {
        int player1HandCount = players.get(0).getPlayerHand().size();
        int player2HandCount = players.get(1).getPlayerHand().size();
        int player3HandCount = 0;
        players.get(0).setScore(player1HandCount);
        players.get(1).setScore(player2HandCount);

        if (players.size() == 3) {
            player3HandCount = players.get(2).getPlayerHand().size();
            players.get(2).setScore(player3HandCount);
        }
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

    boolean emptyHands(ArrayList<Player> players);

    int evaluate(ArrayList<Card> cards);

    void endGame(ArrayList<Player> players);

    void war(ArrayList<Card> cards);
}