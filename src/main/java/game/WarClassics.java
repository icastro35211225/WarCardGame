package game;

import cards.*;
import players.*;

import java.util.ArrayList;

// two player version where cards are not recycled
// where the game ends when the players have used initially given cards
// add won cards into a points pile, instead of back to player
public class WarClassics implements War {
    public Player player1 = new Player();
    public Player player2 = new Player();
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Card> warPile = new ArrayList<Card>();

    public void startGame(Deck deck, long seed, int maxRounds) {
        deck.shuffleDeck(seed);
        initializePlayers();
        gameProcessor.dealCards(deck, players);

        System.out.println("\n***Player1***");
        for (Card card : player1.getPlayerHand()) {
            System.out.println(card.toString());

        }
        System.out.println("\n***Player1***");

        System.out.println("\n***Player2***");
        for (Card card : player2.getPlayerHand()) {
            System.out.println(card.toString());

        }
        System.out.println("\n***Player2***");

        int round = 1;
        while (round <= maxRounds) {
            System.out.println("***Round " + round + "***");
            ArrayList<Card> faceUpCards = new ArrayList<Card>();
            faceUpCards.add(player1.drawCard());
            faceUpCards.add(player2.drawCard());
            printCards(faceUpCards);
            int eval = evaluate(faceUpCards);
            if (eval == -1) {
                break;
            }
            warPile.clear();
            gameProcessor.resetCardValues(player1, player2);
            System.out.println();
            round++;
            if (emptyHands(players)) {
                break;
            }
            // System.out.println("\n***Player1***");
            // for (Card card : player1.getPlayerHand()) {
            // System.out.println(card.toString());

            // }
            // System.out.println("\n***Player1***");

            // System.out.println("\n***Player2***");
            // for (Card card : player2.getPlayerHand()) {
            // System.out.println(card.toString());

            // }
        }
        int count = 0;
        // for (Card card : player2.getPlayerHand()) {
        // System.out.println(count + ") " + card.toString());
        // count++;
        // }
        endGame(players);
    }

    public void initializePlayers() {
        players.add(player1);
        players.add(player2);
    }

    public int evaluate(ArrayList<Card> cards) {
        if (cards.get(0).getCardValue() == cards.get(1).getCardValue()) {
            System.out.println("\n***WAR!***");
            war(cards);
            if (emptyHands(players)) {
                return -1;
            }
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

    public boolean emptyFromWar(ArrayList<Card> cards, Player player) {
        if (player.getPlayerHand().isEmpty()) {
            player2.addCard(cards.get(0));
            player2.addCard(cards.get(1));
            for (Card currCard : warPile) {
                player2.addCard(currCard);
            }
        }
        return false;
    }

    public void war(ArrayList<Card> cards) {
        printCards(cards);
        if (player1.getPlayerHand().size() == 1 || player2.getPlayerHand().size() == 1) {
            return;
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
        gameProcessor.addToWarPile(warPile, cards.get(0));
        gameProcessor.addToWarPile(warPile, cards.get(1));
        System.out.println("player1");
        gameProcessor.addToWarPile(warPile, player1.drawCard());
        System.out.println("player2");
        gameProcessor.addToWarPile(warPile, player2.drawCard());
        if (emptyHands(players)) {
            endGame(players);
            return;
        }

        cards.clear();
        cards.add(player1.drawCard());
        cards.add(player2.drawCard());
        System.out.println("\n***WARPILE!***");

        for (Card curCard : warPile) {
            System.out.println(curCard);
        }
        System.out.println("\n***WARPILE!***");

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