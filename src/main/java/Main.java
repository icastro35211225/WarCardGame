import warcardgame.game.WarClassics;
import warcardgame.game.WarPointsPile;
import warcardgame.players.Player;
import warcardgame.players.PlayerClassic;
import warcardgame.players.PlayerPile;

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
        } else {
            seed = getRandomSeed(args[1]);
        }
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
        ArrayList<PlayerPile> playerPiles = new ArrayList<PlayerPile>();
        PlayerPile playerPile1 = new PlayerPile();
        PlayerPile playerPile2 = new PlayerPile();
        switch (gameType) {
            case 1:
                WarClassics warClassics = new WarClassics();
                ArrayList<PlayerClassic> playerClassics = new ArrayList<PlayerClassic>();
                PlayerClassic playerClassic1 = new PlayerClassic();
                PlayerClassic playerClassic2 = new PlayerClassic();
                playerClassics.add(playerClassic1);
                playerClassics.add(playerClassic2);
                warClassics.startGame(playerClassics, deck, seed, maxRounds);
                break;
            case 2:
                WarPointsPile warPointsPile = new WarPointsPile();
                playerPiles.add(playerPile1);
                playerPiles.add(playerPile2);
                warPointsPile.startGame(playerPiles, deck, seed);
                break;
            case 3:
                WarPointsPile warThreePlayers = new WarPointsPile();
                PlayerPile playerPile3 = new PlayerPile();
                warThreePlayers.startGame(playerPiles, deck, seed);
                break;
        }
    }
}
