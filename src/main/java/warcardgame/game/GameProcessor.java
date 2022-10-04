package warcardgame.game;

import java.util.*;
import warcardgame.cards.*;
import warcardgame.players.Player;

public class GameProcessor {
    public void addToWarPile(ArrayList<Card> warPile, Card card) {
        if (!warPile.contains(card)) {
            warPile.add(card);
        }
    }

    public void calculateScorePile(ArrayList<Player> players) {
        int player1PileCount = players.get(0).getPlayerHand().size();
        int player2PileCount = players.get(1).getPlayerHand().size();
        if (players.size() == 3) {
            int player3PileCount = players.get(2).getPlayerHand().size();
            players.get(2).setScore(player3PileCount);
        }
        players.get(0).setScore(player1PileCount);
        players.get(1).setScore(player2PileCount);
    }

    public void dealCards(Deck deck, ArrayList<Player> players) {
        Card player1Card;
        Card player2Card;
        Card player3Card;
        ArrayList<Card> player1Hand = new ArrayList<Card>();
        ArrayList<Card> player2Hand = new ArrayList<Card>();
        ArrayList<Card> player3Hand = new ArrayList<Card>();

        int i = 0;
        while (i < deck.getCards().size()) {
            if (i == 51) {
                player1Card = deck.getCards().get(i);
                player1Hand.add(player1Card);
                break;
            }
            player1Card = deck.getCards().get(i);
            player1Hand.add(player1Card);
            player2Card = deck.getCards().get(i + 1);
            player2Hand.add(player2Card);
            if (players.size() == 3) {
                player3Card = deck.getCards().get(i + 2);
                player3Hand.add(player3Card);
                i += 3;
            } else {
                i += 2;
            }
        }
        players.get(0).setPlayerHand(player1Hand);
        players.get(1).setPlayerHand(player2Hand);
        if (players.size() == 3) {
            players.get(2).setPlayerHand(player3Hand);
        }
    }

    public void checkAceTwo(ArrayList<Card> cards) {
        if (cards.size() == 3) {
            if (cards.get(0).getCardValue() == 2
                    && (cards.get(1).getCardValue() == 14 || cards.get(2).getCardValue() == 14)) {
                cards.get(0).setCardValue(15);
            } else if (cards.get(1).getCardValue() == 2
                    && (cards.get(0).getCardValue() == 14 || cards.get(2).getCardValue() == 14)) {
                cards.get(1).setCardValue(15);
            } else if (cards.get(2).getCardValue() == 2
                    && (cards.get(1).getCardValue() == 14 || cards.get(0).getCardValue() == 14)) {
                cards.get(2).setCardValue(15);
            }
        }
        if (cards.get(0).getCardValue() == 2 && cards.get(1).getCardValue() == 14) {
            cards.get(0).setCardValue(15);
        } else if (cards.get(0).getCardValue() == 14 && cards.get(1).getCardValue() == 2) {
            cards.get(1).setCardValue(15);
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
        if (players.size() == 3 && players.get(2).getPlayerHand().isEmpty()) {
            return true;
        }
        if (players.get(0).isHandEmpty() || players.get(1).isHandEmpty()) {
            return true;
        }
        return false;
    }

    public ArrayList<Card> checkWar(ArrayList<Card> cards) {
        ArrayList<Card> warCards = new ArrayList<Card>();
        if (cards.size() == 3) {
            if (cards.get(0).getCardValue() == cards.get(1).getCardValue()
                    && cards.get(1).getCardValue() == cards.get(2).getCardValue()) {
                warCards.add(cards.get(0));
                warCards.add(cards.get(1));
                warCards.add(cards.get(2));
            } else if (cards.get(0).getCardValue() == cards.get(1).getCardValue()) {
                warCards.add(cards.get(0));
                warCards.add(cards.get(1));
                return warCards;
            } else if (cards.get(0).getCardValue() == cards.get(2).getCardValue()) {
                warCards.add(cards.get(0));
                warCards.add(cards.get(1));
                return warCards;
            } else if (cards.get(1).getCardValue() == cards.get(2).getCardValue()) {
                warCards.add(cards.get(1));
                warCards.add(cards.get(2));
                return warCards;
            }
        }
        if (cards.get(0).getCardValue() == cards.get(1).getCardValue()) {
            warCards.add(cards.get(0));
            warCards.add(cards.get(1));
            return warCards;
        }
        return null;
    }

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