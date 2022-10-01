package game;

import cards.*;

// This varation has where the cards won, will be put into a 
// a separate points pile. The winner is the player with the most 
// cards in their points pile
public abstract class Variation3 implements Game {
    private Player player3;
    private Hand player3Hand = null;

    public void initializePlayers() {
        players.add(player1);
        players.add(player2);
        players.add(player3);
        playerHands.add(player1Hand);
        playerHands.add(player2Hand);
        playerHands.add(player3Hand);
    }

    public void startGame(int numOfPlayers, Deck deck, long seed) {
        initializePlayers();
        gameProcessor.initializePlayers(numOfPlayers, playerHands);
        deck.shuffleDeck(seed);
        gameProcessor.passOutCards(numOfPlayers, deck, players);

        // Find a way to tell it which variation of the game you are playing
    }
}
