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
