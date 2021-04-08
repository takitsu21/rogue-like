package com.mady;

import com.mady.utils.Map;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.listener.MoveListener;

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
        map = new Map(5, 24, 100);
        map.createMap();
        logger.setLevel(Level.ALL);
        controller = new GameController(map.randomPosPlayerInSalle());
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
     * Render game frames to screen. Here we print bullet position to simulate
     * this process.
     */
    protected void render() {
        System.out.println(map);
    }

    /**
     * execute game loop logic.
     */
    protected abstract void processGameLoop();

}