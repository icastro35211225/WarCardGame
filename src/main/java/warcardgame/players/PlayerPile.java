package warcardgame.players;

import warcardgame.cards.*;
import java.util.ArrayList;
import warcardgame.cards.Deck;

public class PlayerPile {
    private int score = 0;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> cardPile;

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setCardPile(ArrayList<Card> cardPile) {
        this.cardPile = cardPile;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getCardPile() {
        return cardPile;
    }

    public void addCard(Card cardToAdd) {
        if (!cardPile.contains(cardToAdd)) {
            cardPile.add(cardToAdd);
            score++;
        }
    }

    public Card getTopCard() {
        Card card = playerHand.get(0);
        playerHand.remove(0);
        return card;
    }

    public ArrayList<Card> drawCards(ArrayList<PlayerPile> players) {
        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for (PlayerPile currentPlayer : players) {
            drawnCards.add(currentPlayer.getTopCard());
        }
        return drawnCards;
    }

    public void dealCards(Deck deck, ArrayList<PlayerPile> players) {
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
