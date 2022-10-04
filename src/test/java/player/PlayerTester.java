package player;

import warcardgame.players.Player;

import java.util.ArrayList;

import warcardgame.cards.Card;
import warcardgame.cards.Deck;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class PlayerTester
        extends TestCase {

    public void testPlayerHandEmpty() {
        Player player = new Player();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        player.setPlayerHand(playerHand);
        assertTrue(player.isHandEmpty());
    }

    public void testSetAddCard(Card card) {
        Player player = new Player();
        player.addCard(card);
        assertFalse(player.isHandEmpty());
    }

    public void testGetPlayerHand() {
        Player player = new Player();
        Deck deck = new Deck();
        deck.initializeDeck();
        player.setPlayerHand(deck.getCards());
        assertEquals(player.getPlayerHand(), deck.getCards());
    }
}
