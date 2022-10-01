package game;

import java.util.*;
import cards.*;

public class GameProcessor {
    // make this a part of the game object
    public Card player3Card;

    public ArrayList<Player> initializePlayers(int numOfPlayers, ArrayList<Hand> playerHands) {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1 = new Player(playerHands.get(0));
        players.add(player1);
        Player player2 = new Player(playerHands.get(1));
        players.add(player2);
        if (numOfPlayers == 3) {
            Player player3 = new Player(playerHands.get(2));
            players.add(player3);
        } else if (numOfPlayers <= 1 || numOfPlayers > 3) {
            System.err.print("ERROR! Number of players invalid. Players must be 2-3 players");
            return null;
        }
        return players;
    }

    public void passOutCards(int numOfPlayers, Deck deck, ArrayList<Player> players) {
        int count = 1;
        for (int i = 0; i < deck.getCards().size(); i++) {
            Card currentCard = deck.getCards().get(i);
            if (count == 1) {
                players.get(0).getPlayerHand().getCards().add(currentCard);
                count++;
            } else if (count == 2) {
                players.get(1).getPlayerHand().getCards().add(currentCard);
                count++;
            } else if (count == 3) {
                players.get(2).getPlayerHand().getCards().add(currentCard);
                count++;
            }
            if (count == 4) {
                count = 1;
            }
        }
    }

    public Card evaluteCards(ArrayList<Card> cards) {
        Card player1Card = cards.get(0);
        Card player2Card = cards.get(1);
        if (cards.size() == 3) {
            player3Card = cards.get(2);
        }
        if (containsAceAndTwo(cards)) {

        }
        Card winningCard = player1Card;
        if (winningCard.getCardValue() < player2Card.getCardValue()) {
            winningCard = player2Card;
        }
        if (cards.size() == 3) {
            if (winningCard.getCardValue() < player3Card.getCardValue()) {
                winningCard = player3Card;
            }
        }

        return winningCard;
    }

    public boolean containsAceAndTwo(ArrayList<Card> cards) {
        boolean ace = false;
        boolean two = false;
        for (Card card : cards) {
            if (card.getCardRank().equals("ACE"))
                ace = true;
            if (card.getCardRank().equals("TWO"))
                two = true;
        }
        if (ace && two)
            return true;

        return false;
    }
}