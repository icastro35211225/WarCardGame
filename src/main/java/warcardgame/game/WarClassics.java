package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarClassics implements War {
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();

    public void startGame(ArrayList<Player> players, Deck deck, int seed, int maxRounds) {
        deck.shuffleDeck(seed);
        gameProcessor.dealCards(deck, players);

        int round = 1;
        while (round <= maxRounds) {
            if (gameProcessor.emptyHands(players)) {
                endGame(players);
                break;
            }
            System.out.println("***Round " + round + "***");
            ArrayList<Card> drawnCards = gameProcessor.drawCards(players);
            printCards(drawnCards);
            int warCheck = gameProcessor.checkWar(drawnCards);
            if (warCheck == WAR) {
                System.out.println("\n***War!!***");
                war(drawnCards, players);
            } else {
                int winnigCardIndex = evaluate(drawnCards);
                addWinningCard(winnigCardIndex, players, drawnCards);
            }
            if (warPile != null)
                warPile.clear();
            gameProcessor.resetCardValues(players);
            System.out.println();
            round++;
            if (gameProcessor.emptyHands(players)) {
                break;
            }

        }
        endGame(players);
    }

    public int evaluate(ArrayList<Card> cards) {
        int winningIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(winningIndex).getCardValue() < cards.get(i).getCardValue()) {
                winningIndex = i;
            }
        }
        return winningIndex;
    }

    public void war(ArrayList<Card> cards, ArrayList<Player> players) {
        printCards(cards);
        if (players.get(PLAYER1).getPlayerHand().size() <= 1 || players.get(PLAYER2).getPlayerHand().size() <= 1) {
            return;
        }
        if (cards.get(PLAYER1).getCardValue() > cards.get(PLAYER2).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(PLAYER1).addCard(currentCard);
            }
            players.get(PLAYER1).addCard(cards.get(PLAYER2));
            return;
        }
        if (cards.get(PLAYER2).getCardValue() > cards.get(PLAYER1).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(PLAYER2).addCard(currentCard);
            }

            players.get(PLAYER2).addCard(cards.get(PLAYER1));
            return;
        }

        gameProcessor.addToWarPile(warPile, cards.get(0));
        gameProcessor.addToWarPile(warPile, cards.get(1));
        gameProcessor.addToWarPile(warPile, players.get(PLAYER1).drawCard());
        gameProcessor.addToWarPile(warPile, players.get(PLAYER2).drawCard());

        if (gameProcessor.emptyHands(players)) {
            endGame(players);
            return;
        }

        cards.clear();
        cards.add(players.get(PLAYER1).drawCard());
        cards.add(players.get(PLAYER2).drawCard());
        System.out.println("\n***WAR!***");
        war(cards, players);
    }

    public void endGame(ArrayList<Player> players) {
        calculateScore(players);
        if (players.get(0).getScore() == players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 TIES with Player 2");
            printScores(players);
        } else if (players.get(0).getScore() > players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 WINS!!");
            printScores(players);
        } else if (players.get(PLAYER2).getScore() > players.get(PLAYER1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 2 WINS!!");
            printScores(players);
        }
    }

}