package game;

import cards.*;
import players.*;

import java.util.ArrayList;

// two player version where cards are not recycled
// where the game ends when the players have used initially given cards
// add won cards into a points pile, instead of back to player
public class WarPointsPile implements War {
    public Player player1 = new Player();
    public Player player2 = new Player();
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Card> warPile = new ArrayList<Card>();

    public void startGame(int numOfPlayers, Deck deck, int seed) {
        deck.shuffleDeck(seed);
        initializePlayers();
        gameProcessor.dealCards(deck, players);

        while (!emptyHands(players)) {
            ArrayList<Card> faceUpCards = new ArrayList<Card>();
            faceUpCards.add(player1.drawCard());
            faceUpCards.add(player2.drawCard());
            printCards(faceUpCards);
            int eval = evaluate(faceUpCards);
            if (eval == -1) {
                break;
            }
            gameProcessor.resetCardValues(player1, player2);
            System.out.println();
            if (emptyHands(players)) {
                endGame(players);
                break;
            }
        }
    }

    public void initializePlayers() {
        players.add(player1);
        players.add(player2);
    }

    public int evaluate(ArrayList<Card> cards) {
        if (cards.get(0).getCardValue() == cards.get(1).getCardValue()) {
            if (emptyHands(players)) {
                endGame(players);
                return -1;
            }
            System.out.println("\n***WAR!***");
            war(cards);
            warPile.clear();
        }

        gameProcessor.checkAceTwo(cards);

        if (cards.get(0).getCardValue() > cards.get(1).getCardValue()) {
            player1.addCard(cards.get(1));
            player1.addCard(cards.get(1));

            System.out.println("Player 1 wins the round");
        }
        if (cards.get(1).getCardValue() > cards.get(0).getCardValue()) {
            player2.addCard(cards.get(0));
            player2.addCard(cards.get(0));
            System.out.println("Player 2 wins the round");
        }
        return 1;
    }

    public void war(ArrayList<Card> cards) {
        printCards(cards);

        // ArrayList<Card> warPile = new ArrayList<Card>();
        if (player1.getPlayerHand().isEmpty()) {
            for (Card currentCard : warPile) {
                player2.addCard(currentCard);
            }
            endGame(players);
        }
        if (player2.getPlayerHand().isEmpty()) {
            for (Card currentCard : warPile) {
                player1.addCard(currentCard);
            }
            endGame(players);
        }
        if (cards.get(0).getCardValue() > cards.get(1).getCardValue()) {
            for (Card currentCard : warPile) {
                player1.addCard(currentCard);
            }
            player1.addCard(cards.get(1));
            return;
        }
        if (cards.get(1).getCardValue() > cards.get(0).getCardValue()) {
            for (Card currentCard : warPile) {
                player2.addCard(currentCard);
            }

            player2.addCard(cards.get(0));
            return;
        }
        warPile.add(cards.get(0));
        warPile.add(cards.get(1));
        warPile.add(player1.drawCard());
        warPile.add(player2.drawCard());
        if (emptyHands(players)) {
            endGame(players);
            return;
        }

        cards.clear();
        cards.add(player1.drawCard());
        cards.add(player2.drawCard());
        System.out.println("\n***WAR!***");
        war(cards);
    }

    public void endGame(ArrayList<Player> players) {
        calculateScore(players);
        if (players.get(0).getScore() == players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 TIES with Player 2");
            printScores(players);
        } else if (players.get(0).getScore() > players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 WINS!!");
            printScores(players);
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 2 WINS!!");
            printScores(players);
        }
    }

    public boolean emptyHands(ArrayList<Player> players) {
        if (players.get(0).isHandEmpty() || players.get(1).isHandEmpty()) {
            return true;
        }
        return false;
    }
}