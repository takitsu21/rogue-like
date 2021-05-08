package com.mady.game;

public class App {

    /**
     * @param args méthode main du jeu.
     */
    public static void main(String[] args) {
        TurnBasedGameLoop gameLoop = new TurnBasedGameLoop();
        gameLoop.run();
    }
}
