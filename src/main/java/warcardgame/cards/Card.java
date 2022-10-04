package warcardgame.cards;

public class Card {
    private String cardSuit;
    private String cardRank;
    private int cardValue;

    public Card(String cardSuit, String cardRank, int cardValue) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
        this.cardValue = cardValue;
    }

    public void setCardSuit(String cardSuit) {
        this.cardSuit = cardSuit;
    }

    public void setCardRank(String cardRank) {
        this.cardRank = cardRank;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardSuit() {
        return cardSuit;
    }

    public String getCardRank() {
        return cardRank;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String toString() {
        return cardRank + " of " + cardSuit;
    }
}
