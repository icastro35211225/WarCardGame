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

    public void startGame(int numOfPlayers, long seed, int maxRounds, Deck deck) {
        initializePlayers();
        deck.shuffleDeck(seed);
        gameProcessor.passOutCards(numOfPlayers, deck, players);

        int round = 1;
        while (round <= maxRounds) {
            // Take one card from each player, and add it to GameTable class
            // Print out "Player <nmu> plays <RANK> of <SUIT>" for each card
            // Call evaluate to evaluate cards on the table
            // In evaluate, check if the cards match, if so, call war method
            // Do it own like gameEval method
        }
    }
}
