package warcardgame.game;

import java.util.*;
import warcardgame.cards.*;
import warcardgame.players.Player;

public class GameProcessor {
    private static final int PLAYER1 = 0;
    private static final int PLAYER2 = 1;
    private static final int PLAYER3 = 2;
    private static final int OK = 4;
    private static final int WAR = 5;

    public ArrayList<Card> drawCards(ArrayList<Player> players) {
        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for (Player currentPlayer : players) {
            drawnCards.add(currentPlayer.drawCard());
        }
        return drawnCards;
    }

    public void addToWarPile(ArrayList<Card> warPile, Card card) {
        if (!warPile.contains(card)) {
            warPile.add(card);
        }
    }

    public void calculateScorePile(ArrayList<Player> players) {
        for (Player currentPlayer : players) {
            currentPlayer.setScore(currentPlayer.getPlayerHand().size());
        }
    }

    // FIX THIS
    public void dealCards(Deck deck, ArrayList<Player> players) {
        int i = 0;
        while (i < deck.getCards().size()) {
            if (i == 51) {
                players.get(PLAYER1).getPlayerHand().add(deck.getCards().get(i));
                break;
            }
            players.get(PLAYER1).getPlayerHand().add(deck.getCards().get(i));
            players.get(PLAYER2).getPlayerHand().add(deck.getCards().get(i + 1));
            if (players.size() == 3) {
                players.get(PLAYER3).getPlayerHand().add(deck.getCards().get(i + 2));
                i += 3;
            } else {
                i += 2;
            }
        }
    }

    public void checkAceTwo(ArrayList<Card> cards) {
        boolean isThereAce = false;
        boolean isThereTwo = false;
        int twoIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardValue() == 14) {
                isThereAce = true;
            }
            if (cards.get(i).getCardValue() == 2) {
                isThereTwo = true;
                twoIndex = i;
            }
        }
        if (isThereAce && isThereTwo) {
            cards.get(twoIndex).setCardValue(15);
        }
    }

    public void resetCardValues(ArrayList<Player> players) {
        for (Player player : players) {
            for (Card card : player.getPlayerHand()) {
                if (card.getCardValue() == 15) {
                    card.setCardValue(2);
                }
            }
        }
    }

    public boolean emptyHands(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int checkWar(ArrayList<Card> cards) {
        Set<Integer> values = new HashSet<Integer>();
        for (Card currentCard : cards) {
            if (values.add(currentCard.getCardValue()) == false) {
                return WAR;
            }
        }
        return OK;
    }

    // FIX THIS
    public void warThreePlayers(ArrayList<Card> cards, ArrayList<Player> players, ArrayList<Card> warPile) {
        if ((players.get(0).getPlayerHand().size() <= 1 || players.get(1).getPlayerHand().size() <= 1)
                || players.get(2).getPlayerHand().size() <= 1) {
            return;
        }
        if (cards.get(2).getCardValue() > cards.get(1).getCardValue()
                && cards.get(2).getCardValue() > cards.get(0).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(2).addToCardsWonPile(currentCard);
            }
            players.get(2).addToCardsWonPile(cards.get(1));
            players.get(2).addToCardsWonPile(cards.get(0));

            return;
        }
        if (cards.get(1).getCardValue() > cards.get(0).getCardValue()
                && cards.get(1).getCardValue() > cards.get(2).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(1).addToCardsWonPile(currentCard);
            }
            players.get(1).addToCardsWonPile(cards.get(0));
            players.get(1).addToCardsWonPile(cards.get(2));
            return;
        }
        if (cards.get(0).getCardValue() > cards.get(1).getCardValue()
                && cards.get(0).getCardValue() > cards.get(2).getCardValue()) {
            for (Card currentCard : warPile) {
                players.get(0).addToCardsWonPile(currentCard);
            }
            players.get(0).addToCardsWonPile(cards.get(1));
            players.get(0).addToCardsWonPile(cards.get(2));
            return;
        }
        addToWarPile(warPile, cards.get(0));
        addToWarPile(warPile, cards.get(1));
        addToWarPile(warPile, cards.get(2));
        addToWarPile(warPile, players.get(0).drawCard());
        addToWarPile(warPile, players.get(1).drawCard());
        addToWarPile(warPile, players.get(2).drawCard());

        if (emptyHands(players)) {
            return;
        }

        cards.clear();
        cards.add(players.get(0).drawCard());
        cards.add(players.get(1).drawCard());
        cards.add(players.get(2).drawCard());
        System.out.println("\n***WAR!***");
        warThreePlayers(cards, players, warPile);
    }
}