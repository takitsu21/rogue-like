package com.mady;

import com.mady.utils.*;
import com.mady.utils.listener.MoveListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Abstract class for GameLoop implementation class.
 */
public abstract class GameLoop {

    protected final GameController controller;
    protected final Logger logger = Logger.getLogger(GameLoop.class.getName());
    protected volatile GameStatus status;
    protected Frame frame = new Frame();
    protected World world;
    protected Map map;
    private Thread gameThread;


    /**
     * Initialize game status to be stopped.
     */
    public GameLoop() {
        world = new World(frame);
        world.createWorld();
        map = world.getCurrentMap();


        logger.setLevel(Level.ALL);
        Salle salle = map.chooseSalle();
        controller = new GameController(map.randomPosPlayerInSalle(salle), salle);
        map.addPlayerToMap(controller.getPlayer());
        map.addEntityItemPortal();
        render();
        map.getFrame().getFrame().addKeyListener(new MoveListener(map));
        status = GameStatus.STOPPED;
    }

    public static void clrscr() {
        String s;
        Process p;
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                p = Runtime.getRuntime().exec("printf \\33c");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null)
                    System.out.print(s);
                p.waitFor();
                p.destroy();
            }
        } catch (Exception ignored) {}
    }

    /**
     * Run game loop.
     */
    public void run() {
        status = GameStatus.STARTING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    /**
     * Stop game loop.
     */
    public void stop() {
        status = GameStatus.STOPPED;
    }

    public void quit() {
        status = GameStatus.QUITTING;
        System.exit(0);
    }

    /**
     * Check if game is running or not.
     *
     * @return {@code true} if the game is running.
     */
    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    public boolean isGamePaused() {
        return status == GameStatus.PAUSE;
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
            if (Util.keyPressed == KeyboardPressedEnum.I || Util.keyPressed == KeyboardPressedEnum.P) {
                status = GameStatus.PAUSE;
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
        if (isGamePaused() && Util.keyPressed == KeyboardPressedEnum.I) {
            System.out.println(Util.showInventoryMenu(controller.player));
        }
        else if ((isGamePaused() && Util.keyPressed == KeyboardPressedEnum.P)){
            System.out.println(map.getPause().toString(map.getMap()));
        }
//        else if (isGamePaused() && Util.keyPressed == KeyboardPressedEnum.PLUS) {
//            Util.showShop()
//        }
        else if (isGameRunning()) {
            System.out.println(map);
        }
    }

    /**
     * execute game loop logic.
     */
    protected abstract void processGameLoop();

}
