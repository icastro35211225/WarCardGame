package warcardgame.players;

import warcardgame.cards.*;
import java.util.ArrayList;

public class PlayerPile implements Player {
    private int score = 0;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> cardPile;

    public PlayerPile() {
        playerHand = new ArrayList<Card>();
        cardPile = new ArrayList<Card>();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCardPile(ArrayList<Card> cardPile) {
        this.cardPile = cardPile;
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

    public ArrayList<Card> getCardPile() {
        return cardPile;
    }

    public void calculateScore(ArrayList<PlayerPile> players) {
        for (PlayerPile player : players) {
            player.setScore(player.getCardPile().size());
        }
    }
}
