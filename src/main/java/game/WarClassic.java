package game;

import java.util.ArrayList;
import cards.*;

//use interfaces to implement diff variations

// version ehre 2 players play until:
// 1) one player has one all of the cards
// 2) a max number of rounds have been played
public interface WarClassic {
    public Deck deck = new Deck();
    public Player player1 = new Player();
    public Player player2 = new Player();
    public GameProcessor gameProcessor = new GameProcessor();

    public default void startGame(int numOfPlayers, Deck deck, long seed, int maxRounds){
        deck.shuffleDeck(seed);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        gameProcessor.passOutCards(numOfPlayers, deck, players);

        int round = 1;
        while(round <= maxRounds){
            Card player1Card = player1.popCard();
            Card player2Card = player2.popCard();
            printCards(player1Card, player1Card);
            evaluate(player1Card, player2Card);
            
        }
    }

    public default void emptyHands(){
        if(player1.isHandEmpty()){
            
        }
        if(player2.isHandEmpty()){

        }
    }

    public default void printCards(Card player1Card, Card player2Card){
        String player1Rank = player1Card.getCardRank();
        String player1Suit = player1Card.getCardSuit();
        String player2Rank = player2Card.getCardRank();
        String player2Suit = player2Card.getCardSuit();
        System.out.println("Player 1 plays " + player1Rank + " of " + player1Suit);
        System.out.println("Player 2 plays " + player2Rank + " of " + player2Suit);
    }

    public default void evaluate(Card player1Card, Card player2Card){
        if(player1Card.getCardValue() == player2Card.getCardValue()){
            war(player1Card, player2Card);
        }

        checkAceTwo(player1Card, player2Card);
        
        if(player1Card.getCardValue() > player2Card.getCardValue()){
            player1.addCard(player1Card);
            player1.addCard(player2Card);
        }
        if(player2Card.getCardValue() > player1Card.getCardValue()){
            player2.addCard(player1Card);
            player2.addCard(player2Card);
        }
        
    }

    public default void checkAceTwo(Card player1Card, Card player2Card){
        if(player1Card.getCardValue() == 2 && player2Card.getCardValue() == 14){
            player1Card.setCardValue(15);
        }
        else if(player1Card.getCardValue() == 14 && player2Card.getCardValue() == 2){
            player2Card.setCardValue(15);
        }
    }

    public default void war(Card player1Card, Card player2Card){
        ArrayList<Card> warPile = new ArrayList<Card>();
        if(player1Card.getCardValue() > player2Card.getCardValue()){
            for(Card currentCard : warPile){
                player1.addCard(currentCard);
            }
            player1.addCard(player2Card);
            return;
        }
        if(player2Card.getCardValue() > player1Card.getCardValue()){
            for(Card currentCard : warPile){
                player2.addCard(currentCard);
            }
            player2.addCard(player1Card);
            return;
        }
        warPile.add(player1Card);
        warPile.add(player2Card);
        warPile.add(player1.popCard());
        warPile.add(player2.popCard());
        war(player1.popCard(), player2.popCard());
    }
}