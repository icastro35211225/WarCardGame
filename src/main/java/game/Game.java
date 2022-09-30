package game;

import cards.*;

public class Game {
    private Deck deck = new Deck();
    private Player player1 = null, player2 = null, player3 = null;
    private Hand player1Hand = null, player2Hand = null, player3Hand = null;

    public void startGame(int numOfPlayers) {
        initializePlayers(numOfPlayers);
        deck.shuffleDeck();

    }

    public void initializePlayers(int numOfPlayers) {
        player1 = new Player(player1Hand);
        player2 = new Player(player2Hand);
        if (numOfPlayers == 3) {
            player3 = new Player(player3Hand);
        } else if (numOfPlayers <= 1 || numOfPlayers > 3) {
            System.err.print("ERROR! Number of players invalid. Players must be 2-3 players");
        }
    }

    public void passOutCards(int numOfPlayers) {
        int count = 1;
        for (int i = 0; i < deck.getCards().size(); i++) {
            Card currentCard = deck.getCards().get(i);
            if (count == 1) {
                player1.getPlayerHand().getCards().add(currentCard);
                count++;
            } else if (count == 2) {
                player2.getPlayerHand().getCards().add(currentCard);
                count++;
            } else if (count == 3) {
                player3.getPlayerHand().getCards().add(currentCard);
                count++;
            }
            if (count == 4) {
                count = 1;
            }
        }
    }

}