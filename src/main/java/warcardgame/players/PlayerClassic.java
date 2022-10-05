package warcardgame.players;

import warcardgame.cards.Card;
import java.util.ArrayList;

public class PlayerClassic implements Player {
    private int score = 0;
    private ArrayList<Card> playerHand;

    public PlayerClassic() {
        playerHand = new ArrayList<Card>();
    }

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

    public void calculateScore(ArrayList<PlayerClassic> players) {
        for (PlayerClassic player : players) {
            player.setScore(player.getPlayerHand().size());
        }
    }
}
