package game;

import java.util.*;
import cards.*;

public class GameProcessor {

    public void passOutCards(int numOfPlayers, Deck deck, ArrayList<Player> players) {
        Card player1Card;
        Card player2Card;
        Card player3Card;
        for (int i = 0; i < deck.getCards().size();) {
            player1Card = deck.getCards().get(i);
            players.get(0).getPlayerHand().getCards().add(player1Card);
            player2Card = deck.getCards().get(i + 1);
            players.get(1).getPlayerHand().getCards().add(player2Card);
            if (players.size() == 3) {
                player3Card = deck.getCards().get(i + 2);
                players.get(2).getPlayerHand().getCards().add(player3Card);
                i = i + 3;
            }
            i = i + 2;
        }
    }

    public Card evaluteCards(ArrayList<Card> cards) {
        // ADD condition if the cards are equal, WAR method
        Card player1Card = cards.get(0);
        Card player2Card = cards.get(1);
        Card player3Card = null;
        if (cards.size() == 3) {
            player3Card = cards.get(2);
        }
        if (containsAceAndTwo(cards)) {
            // FINISH
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