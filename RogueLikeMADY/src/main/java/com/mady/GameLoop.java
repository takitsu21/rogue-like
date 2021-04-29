package com.mady;

import com.mady.utils.*;
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
    protected final Logger logger = Logger.getLogger(GameLoop.class.getName());
    protected static volatile Frame frame = new Frame();
    protected static volatile World world;
    protected static volatile Map map;
    private Thread gameThread;
    private Thread musicThread;
    private final MusicPlayer audioPlayer = new MusicPlayer();


    /**
     * Initialize game status to be stopped.
     */
    public GameLoop() {
        world = new World(frame);
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
        frame.getFrame().setIconImage(img.getImage());
        frame.getFrame().addKeyListener(new MoveListener(map));
        status = GameStatus.STOPPED;
    }

    public static void restart() {
        world = new World(frame);
        world.createWorld();
        map = world.getCurrentMap();
        Salle salle = map.chooseSalle();
        while (salle.equals(map.getSalleBoss())) {
            salle = map.chooseSalle();
        }
        controller = new GameController(map.randomPosPlayerInSalle(salle), salle);
        map.addPlayerToMap(controller.getPlayer());
        map.addEntityItemPortal();
        for (KeyListener c : frame.getFrame().getListeners(KeyListener.class)) {
            frame.getFrame().removeKeyListener(c);
        }
        frame.getFrame().addKeyListener(new MoveListener(map));
        status = GameStatus.WELCOME_SCREEN;

    }

    public static Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
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
     * Run game loop.
     */
    public void run() {
        status = GameStatus.STARTING;
        gameThread = new Thread(this::processGameLoop);
        musicThread = new Thread(audioPlayer::play);
        musicThread.start();
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

    public boolean isGamePaused() {
        return status == GameStatus.PAUSE;
    }

    public boolean isWelcomeScreen() {
        return status == GameStatus.WELCOME_SCREEN;
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
            if (Util.keyPressed == KeyboardPressedEnum.I || Util.keyPressed == KeyboardPressedEnum.ESC) {
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
        } else if ((isGamePaused() && Util.keyPressed == KeyboardPressedEnum.ESC)) {
            System.out.println(map.getPause().toString(map.getMap()));
        } else if (isWelcomeScreen()) {
            Util.showWelcomeScreen();
        } else if (isGameRunning()) {
            System.out.println(map);
        }
    }

    public static GameStatus getStatus() {
        return status;
    }

    public static void setStatus(GameStatus status) {
        GameLoop.status = status;
    }

    /**
     * execute game loop logic.
     */
    protected abstract void processGameLoop();

}
