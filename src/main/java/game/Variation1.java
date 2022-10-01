package game;

import cards.*;

// version ehre 2 players play until:
// 1) one player has one all of the cards
// 2) a max number of rounds have been played
public abstract class Variation1 implements Game {

    public void initializePlayers() {
        players.add(player1);
        players.add(player2);
        playerHands.add(player1Hand);
        playerHands.add(player2Hand);
    }

    public void startGame(int numOfPlayers, Deck deck, long seed) {
        initializePlayers();
        gameProcessor.initializePlayers(numOfPlayers, playerHands);
        deck.shuffleDeck(seed);
        gameProcessor.passOutCards(numOfPlayers, deck, players);

        // Find a way to tell it which variation of the game you are playing
    }
}