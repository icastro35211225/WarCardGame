package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarPointsPile implements War {
    ArrayList<PlayerPile> players = new ArrayList<PlayerPile>();
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();
    ArrayList<ArrayList<Card>> playerHands = new ArrayList<ArrayList<Card>>();
    PlayerPile playerHandler;

    public void startGame(int numOfPlayers, Deck deck, int seed) {
        initializePlayers(numOfPlayers);
        deck.shuffleDeck(seed);
        dealCards(deck, playerHands);
        playerHandler = players.get(0);
        while (!playerHandler.findEmptyHand(playerHands)) {
            ArrayList<Card> drawnCards = playerHandler.drawCards(playerHands);
            printCards(drawnCards);
            int warCheck = gameProcessor.checkWar(drawnCards);
            if (warCheck == WAR) {
                System.out.println("\n***War!!***");
                war(drawnCards, warPile, playerHands);
            } else {
                int winnigCardIndex = evaluate(drawnCards);
                gameProcessor.printRoundWinner(winnigCardIndex);
                addWinningPile(winnigCardIndex, drawnCards);
            }
            if (warPile != null)
                warPile.clear();
            resetCardValues();
            System.out.println();
            if (playerHandler.findEmptyHand(playerHands)) {
                break;
            }

        }
        endGame();
    }

    public void initializePlayers(int numOfPlayers) {
        PlayerPile player1 = new PlayerPile();
        PlayerPile player2 = new PlayerPile();
        PlayerPile player3 = new PlayerPile();
        players.add(player1);
        playerHands.add(player1.getPlayerHand());
        players.add(player2);
        playerHands.add(player2.getPlayerHand());
        if (numOfPlayers == 3) {
            players.add(player3);
            playerHands.add(player3.getPlayerHand());
        }
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

    public void printScores(ArrayList<PlayerPile> players) {
        for (int i = 1; i <= players.size(); i++) {
            System.out.println("Player " + i + " has a score of " + players.get(i -
                    1).getScore());
        }
    }

    public void resetCardValues() {
        for (PlayerPile player : players) {
            for (Card card : player.getPlayerHand()) {
                if (card.getCardValue() == 15) {
                    card.setCardValue(2);
                }
            }
        }
    }

    public void addWinningPile(int winnigIndex, ArrayList<Card> drawnCards) {
        for (Card curCard : drawnCards) {
            players.get(winnigIndex).getCardPile().add(curCard);
        }
    }

}