import warcardgame.game.WarClassics;
import warcardgame.game.WarPointsPile;
import warcardgame.players.Player;

import java.util.ArrayList;

import warcardgame.cards.Deck;

public class Main {

    public static void main(String[] args) {
        int gameType = getGameType(args[0]);
        int maxRounds = 0;
        int seed = -1;
        if (gameType == 1) {
            maxRounds = getMaxRounds(args[1]);
            seed = getRandomSeed(args[2]);
        }
        seed = getRandomSeed(args[1]);
        Deck deck = new Deck();
        runGame(gameType, maxRounds, seed, deck);
    }

    public static int getGameType(String gameTypeCommadLine) {
        int gameType = -1;
        try {
            gameType = Integer.parseInt(gameTypeCommadLine);
        } catch (Exception e) {
            System.err.println("Game Type Argument ERROR!!");
            System.exit(-2);
        }
        return gameType;
    }

    public static int getMaxRounds(String roundsCommandLine) {
        int maxRounds = -1;
        try {
            maxRounds = Integer.parseInt(roundsCommandLine);
        } catch (Exception e) {
            System.err.println("Max Round Argument ERROR!!");
            System.exit(-3);
        }
        return maxRounds;
    }

    public static int getRandomSeed(String seedCommandLine) {
        int seed = -1;
        try {
            seed = Integer.parseInt(seedCommandLine);
        } catch (Exception e) {
            System.err.println("Seed Argument ERROR!!");
            System.exit(-4);
        }
        return seed;
    }

    public static void runGame(int gameType, int maxRounds, int seed, Deck deck) {
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1 = new Player();
        Player player2 = new Player();
        switch (gameType) {
            case 1:
                WarClassics warClassics = new WarClassics();
                players.add(player1);
                players.add(player2);
                warClassics.startGame(players, deck, seed, maxRounds);
                break;
            case 2:
                WarPointsPile warPointsPile = new WarPointsPile();
                players.add(player1);
                players.add(player2);
                warPointsPile.startGame(players, deck, seed);
                break;
            case 3:
                WarPointsPile warThreePlayers = new WarPointsPile();
                Player player3 = new Player();
                warThreePlayers.startGame(players, deck, seed);
                break;
        }
    }
}
