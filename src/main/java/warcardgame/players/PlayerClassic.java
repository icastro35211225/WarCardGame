package warcardgame.players;

import warcardgame.cards.Card;
import java.util.ArrayList;
import warcardgame.cards.Deck;;

public class PlayerClassic implements Player {
    private int score = 0;
    private ArrayList<Card> playerHand;

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void addCard(Card card) {
        if (!playerHand.contains(card)) {
            playerHand.add(card);
        }
    }

    public Card getTopCard() {
        Card card = playerHand.get(0);
        playerHand.remove(0);
        return card;
    }

    public ArrayList<Card> drawCards(ArrayList<PlayerClassic> players) {
        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for (PlayerClassic currentPlayer : players) {
            drawnCards.add(currentPlayer.getTopCard());
        }
        return drawnCards;
    }

    public void dealCards(Deck deck, ArrayList<PlayerClassic> players) {
        int i = 0;
        while (i < deck.getCards().size()) {
            if (i == 51) {
                players.get(0).getPlayerHand().add(deck.getCards().get(i));
                break;
            }
            players.get(0).getPlayerHand().add(deck.getCards().get(i));
            players.get(1).getPlayerHand().add(deck.getCards().get(i + 1));
            if (players.size() == 3) {
                players.get(2).getPlayerHand().add(deck.getCards().get(i + 2));
                i += 3;
            } else {
                i += 2;
            }
        }
    }
}
