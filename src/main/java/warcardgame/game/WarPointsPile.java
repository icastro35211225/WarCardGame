package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarPointsPile implements War {
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();

    public void startGame(ArrayList<PlayerPile> players, Deck deck, int seed) {
        deck.shuffleDeck(seed);
        gameProcessor.dealCards(deck, players);

        while (!gameProcessor.emptyHands(players)) {
            ArrayList<Card> drawnCards = gameProcessor.drawCards(players);
            printCards(drawnCards);
            int warCheck = gameProcessor.checkWar(drawnCards);
            if (warCheck == WAR) {
                System.out.println("\n***War!!***");
                war(drawnCards, players, warPile);
            } else {
                int winnigCardIndex = evaluate(drawnCards);
                gameProcessor.printRoundWinner(winnigCardIndex);
                addWinningCard(winnigCardIndex, players, drawnCards);
            }
            if (warPile != null)
                warPile.clear();
            gameProcessor.resetCardValues(players);
            System.out.println();
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

    public void calculateScore(ArrayList<Player> players) {
        for (Player player : players) {
            player.setScore(player.getCardsWonPile().size());
        }
    }

    public void passWinnerCards(int winner, ArrayList<Player> players, ArrayList<Card> cards) {
        for (Card currentCard : cards) {
            players.get(winner).getCardsWonPile().add(currentCard);
        }
    }

    public void addWinningCard(int winnigIndex, ArrayList<Player> players, ArrayList<Card> drawnCards) {
        for (Card currentCard : drawnCards) {
            players.get(winnigIndex).getCardsWonPile().add(currentCard);
        }
    }

    public void endGame(ArrayList<Player> players) {
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

    public void dealCards(Deck deck, ArrayList<Player> players) {
        int i = 0;
        while (i < deck.getCards().size()) {
            if (i == 51) {
                players.get(PLAYER1).getPlayerHand().add(deck.getCards().get(i));
                break;
            }
            players.get(PLAYER1).getPlayerHand().add(deck.getCards().get(i));
            players.get(PLAYER2).getPlayerHand().add(deck.getCards().get(i + 1));
            if (players.size() == 3) {
                players.get(PLAYER3).getPlayerHand().add(deck.getCards().get(i + 2));
                i += 3;
            } else {
                i += 2;
            }
        }
    }
}