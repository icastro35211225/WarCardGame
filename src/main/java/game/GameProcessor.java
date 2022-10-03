package game;

import java.util.*;
import cards.*;

public class GameProcessor {

    public void passOutCards(int numOfPlayers, Deck deck, ArrayList<Player> players) {
        Card player1Card;
        Card player2Card;
        Card player3Card;
        ArrayList<Card> player1Hand = new ArrayList<Card>();
        ArrayList<Card> player2Hand = new ArrayList<Card>();
        ArrayList<Card> player3Hand = new ArrayList<Card>();
        
        for (int i = 0; i < deck.getCards().size();) {
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
        if(numOfPlayers == 3){
            players.get(2).setPlayerHand(player3Hand);
        }
    }
}