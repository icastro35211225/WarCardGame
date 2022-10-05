package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.cards.Deck;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarClassics implements War {
    ArrayList<PlayerClassic> players = new ArrayList<PlayerClassic>();
    ArrayList<Card> warPile = new ArrayList<Card>();
    ArrayList<ArrayList<Card>> playerHands = new ArrayList<ArrayList<Card>>();
    PlayerClassic playerHandler;

    public void startGame(Deck deck, int seed, int maxRounds) {
        initializePlayers();
        deck.shuffleDeck(seed);
        dealCards(deck, playerHands);
        playerHandler = players.get(0);
        int round = 1;
        while (round <= maxRounds) {
            if (playerHandler.findEmptyHand(playerHands)) {
                endGame();
                break;
            }
            System.out.println("***Round " + round + "***");
            ArrayList<Card> drawnCards = playerHandler.drawCards(playerHands);
            printCards(drawnCards);
            int warCheck = checkWar(drawnCards);
            if (warCheck == WAR) {
                System.out.println("\n***War!!***");
                war(drawnCards, warPile, playerHands);
            } else {
                int winnigCardIndex = evaluate(drawnCards);
                printRoundWinner(winnigCardIndex);
                passWinnerCards(winnigCardIndex, playerHands, drawnCards);
            }
            if (warPile != null)
                warPile.clear();
            resetCardValues();
            System.out.println();
            round++;
            if (playerHandler.findEmptyHand(playerHands)) {
                break;
            }

        }
        endGame();
    }

    public void initializePlayers() {
        PlayerClassic player1 = new PlayerClassic();
        PlayerClassic player2 = new PlayerClassic();
        players.add(player1);
        playerHands.add(player1.getPlayerHand());
        players.add(player2);
        playerHands.add(player2.getPlayerHand());
    }

    public void endGame() {
        playerHandler.calculateScore(players);
        int winnigIndex = 0;
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getScore() > players.get(winnigIndex).getScore()) {
                winnigIndex = i;
            } else if (players.get(i).getScore() == players.get(winnigIndex).getScore()) {
                System.out.println("There's a TIE!!");
                printScores(players);
                return;
            }
        }
        System.out.println("***GAME OVER***\nPlayer " + (winnigIndex + 1) + " WINS!");
        printScores(players);
    }

    public void printScores(ArrayList<PlayerClassic> players) {
        for (int i = 1; i <= players.size(); i++) {
            System.out.println("Player " + i + " has a score of " + players.get(i -
                    1).getScore());
        }
    }

    public void resetCardValues() {
        for (PlayerClassic player : players) {
            for (Card card : player.getPlayerHand()) {
                if (card.getCardValue() == 15) {
                    card.setCardValue(2);
                }
            }
        }
    }

    public boolean checkForTie() {
        for (PlayerClassic winningPlayer : players) {
            for (PlayerClassic currentPlayer : players) {
                if (currentPlayer.getScore() == winningPlayer.getScore()) {
                    return true;
                }
            }
        }
        return false;
    }

}