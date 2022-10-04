package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.cards.Deck;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarClassics implements War {
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();

    public void startGame(ArrayList<PlayerClassic> players, Deck deck, int seed, int maxRounds) {
        deck.shuffleDeck(seed);
        dealCards(deck, players);

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

    public void dealCards(Deck deck, ArrayList<PlayerClassic> players) {
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