package game;

import cards.*;

// version ehre 2 players play until:
// 1) one player has one all of the cards
// 2) a max number of rounds have been played
public abstract class Variation1 implements Game {

    public void addPlayersToArrayList() {
        players.add(player1);
        players.add(player2);
        playerHands.add(player1Hand);
        playerHands.add(player2Hand);
    }

    public void initializeGame(int numOfPlayers, long seed, int maxRounds, Deck deck) {
        addPlayersToArrayList();
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