package game;

import cards.*;

public class Player {
    private int score = 0;
    private Hand playerHand;

    public Player(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void addCard(Card card){
        playerHand.addToPlayerHand(card);
    }

}