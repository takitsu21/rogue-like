package com.mady;

import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.listener.MoveListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Abstract class for GameLoop implementation class.
 */
public abstract class GameLoop {

    protected volatile GameStatus status;

    protected final GameController controller;

    protected Map map;

    private Thread gameThread;


    protected final Logger logger = Logger.getLogger(GameLoop.class.getName());


    /**
     * Initialize game status to be stopped.
     */
    public GameLoop() {
//        System.setProperty("java.awt.headless", "false");

        map = new Map(5, 24, 100);
        map.createMap();
        logger.setLevel(Level.ALL);
        Salle salle= map.chooseSalle();
        controller = new GameController(map.randomPosPlayerInSalle(salle), salle);
        map.addPlayerToMap(controller.getPlayer());
        map.getFrame().getFrame().addKeyListener(new MoveListener(map));
        render();
        status = GameStatus.STOPPED;
    }

    /**
     * Run game loop.
     */
    public void run() {
        status = GameStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    /**
     * Stop game loop.
     */
    public void stop() {
        status = GameStatus.STOPPED;
    }

    /**
     * Check if game is running or not.
     *
     * @return {@code true} if the game is running.
     */
    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    /**
     * Handle any user input that has happened since the last call. In order to
     * simulate the situation in real-life game, here we add a random time lag.
     * The time lag ranges from 50 ms to 250 ms.
     */
    protected void processInput() {
        try {
            while (Util.playerTurn) {
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * Render game frames to screen. Here we print the map.
     */
    protected void render() {
        clrscr();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(map);
    }

    public static void clrscr() {
        try {

            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    }

    /**
     * execute game loop logic.
     */
    protected abstract void processGameLoop();

}