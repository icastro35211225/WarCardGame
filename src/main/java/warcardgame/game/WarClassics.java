package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.cards.Deck;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarClassics implements War {
    ArrayList<PlayerClassic> players = new ArrayList<PlayerClassic>();
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();
    ArrayList<ArrayList<Card>> playerHands = new ArrayList<ArrayList<Card>>();
    PlayerClassic playerHandler = players.get(0);

    public void startGame(Deck deck, int seed, int maxRounds) {
        deck.shuffleDeck(seed);
        dealCards(deck, playerHands);

        int round = 1;
        while (round <= maxRounds) {
            if (playerHandler.findEmptyHand(playerHands)) {
                endGame();
                break;
            }
            System.out.println("***Round " + round + "***");
            ArrayList<Card> drawnCards = playerHandler.drawCards(playerHands);
            printCards(drawnCards);
            int warCheck = gameProcessor.checkWar(drawnCards);
            if (warCheck == WAR) {
                System.out.println("\n***War!!***");
                war(drawnCards, warPile, playerHands);
            } else {
                int winnigCardIndex = evaluate(drawnCards);
                gameProcessor.printRoundWinner(winnigCardIndex);
                addWinningCard(winnigCardIndex, playerHands, drawnCards);
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

    public void endGame() {
        playerHandler.calculateScore(players);
        int winnigIndex = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() > players.get(winnigIndex).getScore()) {
                winnigIndex = i;
            }
            if (i != 0 && players.get(i).getScore() == players.get(winnigIndex).getScore()) {
                if (checkForTie()) {
                    System.out.println("There's a TIE!!");
                    printScores(players);
                }
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