package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarPointsPile implements War {
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();

    public void startGame(ArrayList<Player> players, Deck deck, int seed) {
        deck.shuffleDeck(seed);
        gameProcessor.dealCards(deck, players);

        while (!gameProcessor.emptyHands(players)) {
            if (gameProcessor.emptyHands(players)) {
                endGame(players);
                break;
            }
            ArrayList<Card> drawnCards = gameProcessor.drawCards(players);
            printCards(drawnCards);
            int warCheck = gameProcessor.checkWar(drawnCards);
            if (warCheck == WAR) {
                System.out.println("\n***War!!***");
                war(drawnCards, players);
            }
            gameProcessor.resetCardValues(players);
            System.out.println();
            if (gameProcessor.emptyHands(players)) {
                endGame(players);
                break;
            }
        }
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
        if (cards.get(0).getCardValue() > cards.get(1).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(PLAYER1).addToCardsWonPile(currentCard);
            }
            players.get(PLAYER1).addToCardsWonPile(cards.get(1));
            return;
        }
        if (cards.get(1).getCardValue() > cards.get(0).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(PLAYER2).addToCardsWonPile(currentCard);
            }

            players.get(PLAYER2).addToCardsWonPile(cards.get(0));
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
        if (players.get(0).getScore() == players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 TIES with Player 2");
            printScores(players);
        } else if (players.get(0).getScore() > players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 WINS!!");
            printScores(players);
        } else if (players.get(PLAYER2).getScore() > players.get(PLAYER1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 2 WINS!!");
            printScores(players);
        } else if ((players.size() == 3) && players.get(2).getScore() > players.get(1).getScore()
                && players.get(2).getScore() > players.get(0).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 3 WINS!!");
            printScores(players);
        } else if ((players.size() == 3) && players.get(1).getScore() > players.get(0).getScore()
                && players.get(1).getScore() > players.get(2).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 2 WINS!!");
            printScores(players);
        } else if ((players.size() == 3) && players.get(0).getScore() > players.get(1).getScore()
                && players.get(0).getScore() > players.get(2).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 WINS!!");
            printScores(players);
        }
    }
}