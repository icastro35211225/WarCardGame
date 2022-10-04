package players;

import cards.*;
import java.util.ArrayList;

public class Player {
    private int score = 0;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> cardsWonPile;

    public Player() {
        playerHand = new ArrayList<Card>();
        cardsWonPile = new ArrayList<Card>();
    }

    public Player(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCardsWonPile(ArrayList<Card> cardWonPile) {
        this.cardsWonPile = cardWonPile;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getCardsWonPile() {
        return cardsWonPile;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void addCard(Card card) {
        if (!playerHand.contains(card))
            playerHand.add(card);
    }

    public void addToCardsWonPile(Card cardToAdd) {
        cardsWonPile.add(cardToAdd);
        score++;
    }

    public boolean isHandEmpty() {
        if (playerHand.isEmpty()) {
            return true;
        }
        return false;
    }

    public void removeCard(Card cardToRemove) {
        playerHand.remove(cardToRemove);
    }

    public Card drawCard() {
        Card card = playerHand.get(0);
        playerHand.remove(0);
        return card;
    }

    public void printHand() {
        for (Card card : playerHand) {
            System.out.println(card.getCardRank() + " of " + card.getCardSuit());
        }
    }

}