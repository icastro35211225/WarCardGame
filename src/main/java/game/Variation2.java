package game;

import cards.*;

// two player version where cards are not recycled
// where the game ends when the players have used initially given cards
public abstract class Variation2 implements Game {

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
