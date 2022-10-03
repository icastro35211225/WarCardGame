package game;

import cards.*;
import java.util.ArrayList;

public class Player {
    private int score = 0;
    private ArrayList<Card> playerHand;

    public Player() {

    }
    
    public Player(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void addCard(Card card){
        playerHand.add(card);
    }

    public boolean isHandEmpty(){
        if(playerHand.isEmpty()){
            return true;
        }
        return false;
    }

    public void removeCard(Card cardToRemove){
        playerHand.remove(cardToRemove);
    }

    public Card popCard(){
        Card card = playerHand.get(0);
        playerHand.remove(card);
        return card;
    }

}