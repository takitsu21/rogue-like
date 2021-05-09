package com.mady.game;

import com.mady.utils.Util;
import com.mady.utils.enums.KeyboardPressedEnum;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;
import com.mady.utils.environment.World;
import com.mady.utils.listener.MoveListener;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Abstract class for GameLoop implementation class.
 */
public abstract class GameLoop {

    protected static volatile GameStatus status;
    protected static volatile GameController controller;
    protected static volatile WindowGameIntegration windowGameIntegration = new WindowGameIntegration();
    protected static volatile World world;
    protected static volatile Map map;
    protected final Logger logger = Logger.getLogger(GameLoop.class.getName());
    private Thread gameThread;
    //private Thread musicThread;
    //private final MusicPlayer audioPlayer = new MusicPlayer();


    /**
     * Initialize game status to be stopped.
     */
    public GameLoop() {
        world = new World(windowGameIntegration);
        world.createWorld();
        map = world.getCurrentMap();


        logger.setLevel(Level.ALL);
        Salle salle = map.chooseSalle();
        while (salle.equals(map.getSalleBoss())) {
            salle = map.chooseSalle();
        }
        controller = new GameController(map.randomPosPlayerInSalle(salle), salle);
        map.addPlayerToMap(controller.getPlayer());
        map.addEntityItemPortal();
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/MAD16x16.png")));
        windowGameIntegration.getFrame().setIconImage(img.getImage());
        windowGameIntegration.getFrame().addKeyListener(new MoveListener(map));
        status = GameStatus.STOPPED;
    }

    public static void restart() {
        World.compteur = 0;
        world = new World(windowGameIntegration);
        world.createWorld();
        map = world.getCurrentMap();
        Salle salle = map.chooseSalle();
        while (salle.equals(map.getSalleBoss())) {
            salle = map.chooseSalle();
        }
        controller = new GameController(map.randomPosPlayerInSalle(salle), salle);
        map.addPlayerToMap(controller.getPlayer());
        map.addEntityItemPortal();
        for (KeyListener c : windowGameIntegration.getFrame().getListeners(KeyListener.class)) {
            windowGameIntegration.getFrame().removeKeyListener(c);
        }
        windowGameIntegration.getFrame().addKeyListener(new MoveListener(map));
        status = GameStatus.WELCOME_SCREEN;
    }

    public static WindowGameIntegration getFrame() {
        return windowGameIntegration;
    }

    public void setFrame(WindowGameIntegration windowGameIntegration) {
        this.windowGameIntegration = windowGameIntegration;
    }

    public static void clrscr() {
        String s;
        Process p;
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                p = Runtime.getRuntime().exec("printf \\33c");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null)
                    System.out.print(s);
                p.waitFor();
                p.destroy();
            }
        } catch (Exception ignored) {
        }
    }

    public static void quit() {
        status = GameStatus.QUITTING;
        System.exit(0);
    }

    /**
     * Check if game is running or not.
     *
     * @return {@code true} if the game is running.
     */
    public static boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    public static boolean isGamePaused() {
        return status == GameStatus.PAUSE;
    }

    public static boolean isWelcomeScreen() {
        return status == GameStatus.WELCOME_SCREEN;
    }

    public static boolean isGameAttackMenu() {
        return GameStatus.RANGE_ATTACK_CHOICE == status;
    }

    public static GameStatus getStatus() {
        return status;
    }

    public static void setStatus(GameStatus status) {
        GameLoop.status = status;
    }

    /**
     * Run game loop.
     */
    public void run() {
        status = GameStatus.STARTING;
        gameThread = new Thread(this::processGameLoop);
        //musicThread = new Thread(audioPlayer::play);
        //musicThread.start();
        gameThread.start();
    }

    /**
     * Stop game loop.
     */
    public void stop() {
        status = GameStatus.STOPPED;
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
            if (Util.keyPressed == KeyboardPressedEnum.I || Util.keyPressed == KeyboardPressedEnum.ESC || Util.keyPressed == KeyboardPressedEnum.SELL) {
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
        if (isGamePaused() && (Util.keyPressed == KeyboardPressedEnum.I || Util.keyPressed == KeyboardPressedEnum.SELL)) {
            System.out.println(Util.showInventoryMenu(controller.player));
        } else if ((isGamePaused() && Util.keyPressed == KeyboardPressedEnum.ESC)) {
            System.out.println(map.getPause().toString(map.getMap(),map.getPlayer()));
        } else if (isWelcomeScreen()) {
            Util.showWelcomeScreen();
        } else if (isGameRunning() || isGameAttackMenu()) {
            System.out.println(map);
        }
    }

    /**
     * execute game loop logic.
     */
    protected abstract void processGameLoop();

}
