package game;

import java.util.ArrayList;
import cards.*;

public class GameTable {
    private ArrayList<Card> cardsOnTable;

    public GameTable() {
        cardsOnTable = new ArrayList<Card>();
    }

    public void printTable() {
        for (Card card : cardsOnTable) {
            System.out.println(card);
        }
    }

    public ArrayList<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public void addCardToTable(Card card) {
        cardsOnTable.add(card);
    }

    public void clearCardsOnTable() {
        cardsOnTable.clear();
    }

    public void giveWinnerCards(Player player){
        for(Card card : cardsOnTable){
            player.addCard(card);
        }
        clearCardsOnTable();
    }
}