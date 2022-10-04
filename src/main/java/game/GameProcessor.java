package game;

import java.util.*;
import cards.*;
import players.Player;

public class GameProcessor {

    public void printHand(ArrayList<Card> playerHand) {
        System.out.println("\n********PlayerHand***********");
        for (Card card : playerHand) {
            System.out.println(card.getCardRank() + " of " + card.getCardSuit());
        }
    }

    public void addToWarPile(ArrayList<Card> warPile, Card card) {
        if (!warPile.contains(card)) {
            warPile.add(card);
        }
    }

    public void dealCards(Deck deck, ArrayList<Player> players) {
        Card player1Card;
        Card player2Card;
        Card player3Card;
        ArrayList<Card> player1Hand = new ArrayList<Card>();
        ArrayList<Card> player2Hand = new ArrayList<Card>();
        ArrayList<Card> player3Hand = new ArrayList<Card>();

        int i = 0;
        while (i < deck.getCards().size()) {
            player1Card = deck.getCards().get(i);
            player1Hand.add(player1Card);
            player2Card = deck.getCards().get(i + 1);
            player2Hand.add(player2Card);
            if (players.size() == 3) {
                player3Card = deck.getCards().get(i + 2);
                player3Hand.add(player3Card);
                i = i + 3;
            }
            i = i + 2;
        }
        players.get(0).setPlayerHand(player1Hand);
        players.get(1).setPlayerHand(player2Hand);
        if (players.size() == 3) {
            players.get(2).setPlayerHand(player3Hand);
        }
    }

    public void checkAceTwo(ArrayList<Card> cards) {
        if (cards.get(0).getCardValue() == 2 && cards.get(1).getCardValue() == 14) {
            cards.get(0).setCardValue(15);
        } else if (cards.get(0).getCardValue() == 14 && cards.get(1).getCardValue() == 2) {
            cards.get(1).setCardValue(15);
        }

        // ADD FOR 3 PLAYERS
    }

    public void resetCardValues(Player player1, Player player2) {
        for (Card card : player1.getPlayerHand()) {
            if (card.getCardValue() == 15) {
                card.setCardValue(2);
            }
        }

        for (Card card : player2.getPlayerHand()) {
            if (card.getCardValue() == 15) {
                card.setCardValue(2);
            }
        }
    }
}