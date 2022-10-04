package cards;

import warcardgame.players.Player;

import java.util.ArrayList;

import warcardgame.cards.Card;
import warcardgame.cards.Deck;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DeckTester
        extends TestCase {

    public void testInitializedDeck() {
        Deck deck = new Deck();
        deck.initializeDeck();
        assertEquals(deck.getCards().size(), 52);
    }

    public void testGetPlayerHand() {
        Deck deck = new Deck();

        deck.initializeDeck();
        // assertEquals(player.getPlayerHand(), deck.getCards());
    }
}
