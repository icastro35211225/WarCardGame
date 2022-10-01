package game;

import java.util.ArrayList;
import cards.*;

//use interfaces to implement diff variations

public interface Game {
    public Deck deck = new Deck();
    public Player player1 = null;
    public Player player2 = null;
    public Hand player1Hand = null;
    public Hand player2Hand = null;
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Hand> playerHands = new ArrayList<Hand>();
    public GameProcessor gameProcessor = new GameProcessor();

    public void initializePlayers();

    public void startGame(int numOfPlayers, Deck deck, long seed, int maxRounds);

}