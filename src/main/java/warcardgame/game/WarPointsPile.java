package warcardgame.game;

import warcardgame.cards.*;
import warcardgame.players.*;

import java.util.ArrayList;

public class WarPointsPile implements War {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Player player3 = new Player();
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Card> warPile = new ArrayList<Card>();
    GameProcessor gameProcessor = new GameProcessor();

    public void startGame(int numOfPlayers, Deck deck, int seed) {
        deck.shuffleDeck(seed);
        initializePlayers(numOfPlayers);
        gameProcessor.dealCards(deck, players);

        while (!gameProcessor.emptyHands(players)) {
            ArrayList<Card> faceUpCards = new ArrayList<Card>();
            faceUpCards.add(player1.drawCard());
            faceUpCards.add(player2.drawCard());
            printCards(faceUpCards);
            int eval = evaluate(faceUpCards);
            if (eval == -1) {
                break;
            }
            gameProcessor.resetCardValues(players);
            System.out.println();
            if (gameProcessor.emptyHands(players)) {
                endGame(players);
                break;
            }
        }
    }

    public void initializePlayers(int numOfPlayers) {
        players.add(player1);
        players.add(player2);
        if (numOfPlayers == 3) {
            players.add(player3);
        }
    }

    public int evaluate(ArrayList<Card> cards) {
        warPile = gameProcessor.checkWar(cards);
        if (warPile != null) {
            System.out.println("\n***WAR!***");
            if (cards.size() == 3) {
                gameProcessor.warThreePlayers(cards, players, warPile);
            } else {
                war(cards);
            }
            if (gameProcessor.emptyHands(players)) {
                return -1;
            }
        }

        gameProcessor.checkAceTwo(cards);

        if (cards.size() == 3) {
            if (cards.get(0).getCardValue() > cards.get(1).getCardValue()
                    && cards.get(0).getCardValue() > cards.get(2).getCardValue()) {
                for (Card card : cards) {
                    player1.addToCardsWonPile(card);
                }
            } else if (cards.get(1).getCardValue() > cards.get(0).getCardValue()
                    && cards.get(1).getCardValue() > cards.get(2).getCardValue()) {
                for (Card card : cards) {
                    player2.addToCardsWonPile(card);
                }
            } else if (cards.get(2).getCardValue() > cards.get(0).getCardValue()
                    && cards.get(2).getCardValue() > cards.get(1).getCardValue()) {
                for (Card card : cards) {
                    player3.addToCardsWonPile(card);
                }
            }
        }
        if (cards.get(0).getCardValue() > cards.get(1).getCardValue()) {
            player1.addToCardsWonPile(cards.get(0));
            player1.addToCardsWonPile(cards.get(1));

            System.out.println("Player 1 wins the round");
        }
        if (cards.get(1).getCardValue() > cards.get(0).getCardValue()) {
            player2.addToCardsWonPile(cards.get(0));
            player2.addToCardsWonPile(cards.get(1));
            System.out.println("Player 2 wins the round");
        }
        return 1;
    }

    public void war(ArrayList<Card> cards) {
        printCards(cards);
        if (player1.getPlayerHand().size() <= 1 || player2.getPlayerHand().size() <= 1) {
            return;
        }
        if (cards.get(0).getCardValue() > cards.get(1).getCardValue()) {
            for (Card currentCard : warPile) {
                player1.addToCardsWonPile(currentCard);
            }
            player1.addToCardsWonPile(cards.get(1));
            return;
        }
        if (cards.get(1).getCardValue() > cards.get(0).getCardValue()) {
            for (Card currentCard : warPile) {
                player2.addToCardsWonPile(currentCard);
            }

            player2.addToCardsWonPile(cards.get(0));
            return;
        }
        gameProcessor.addToWarPile(warPile, cards.get(0));
        gameProcessor.addToWarPile(warPile, cards.get(1));
        gameProcessor.addToWarPile(warPile, player1.drawCard());
        gameProcessor.addToWarPile(warPile, player2.drawCard());
        if (gameProcessor.emptyHands(players)) {
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
        if (players.get(0).getScore() == players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 TIES with Player 2");
            printScores(players);
        } else if (players.get(0).getScore() > players.get(1).getScore()) {
            System.out.println("\n***GAME OVER***\nPlayer 1 WINS!!");
            printScores(players);
        } else if (player2.getScore() > player1.getScore()) {
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