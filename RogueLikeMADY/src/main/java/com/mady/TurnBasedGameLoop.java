package com.mady;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.KeyboardPressedEnum;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Shop;
import com.mady.utils.listener.MoveListener;

import java.awt.event.KeyListener;

public class TurnBasedGameLoop extends GameLoop {

    /**
     * control of the turn by turn
     */
    @Override
    protected void processGameLoop() {
        if (status == GameStatus.STARTING) {
            status = GameStatus.WELCOME_SCREEN;
            render();
        }
        while (isWelcomeScreen()) {
            processInput();
            if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                status = GameStatus.RUNNING;
            }
            render();
            Util.playerTurn = true;
        }
        while (isGameRunning()) {

            processInput();
            if (isGamePaused()) {
                while (isGamePaused()) {
                    processInput();
                    if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                        status = GameStatus.RUNNING;
                    }
                    render();
                    Util.playerTurn = true;
                }
            } else {
                for (Entities entitie : world.getCurrentMap().getEntities()) {
                    map = entitie.doTurn(world.getCurrentMap());

                }

                if (map.getMap()[map.getPlayer().getPosition().getX()][map.getPlayer().getPosition().getY()].isPortal()) {
                    world.addMap();
                    Salle salle = world.getCurrentMap().chooseSalle();
                    while (salle.equals(map.getSalleBoss())) {
                        salle = map.chooseSalle();
                    }
                    Position position = world.getCurrentMap().randomPosPlayerInSalle(salle);
                    map.getPlayer().setPos(position);
                    world.getCurrentMap().addPlayerToMap(map.getPlayer());
                    world.getCurrentMap().addEntityItemPortal();
                    map = world.getCurrentMap();
                    for (KeyListener c : frame.getFrame().getListeners(KeyListener.class)) {
                        frame.getFrame().removeKeyListener(c);
                    }
                    frame.getFrame().addKeyListener(new MoveListener(map));
//                    render();
//                    System.out.println(Util.showShop(controller.player));
//                    status = GameStatus.PAUSE;
//                    Util.keyPressed = KeyboardPressedEnum.PLUS;
//                    continue;
                }
                if(map.getMap()[map.getPlayer().getPosition().getX()][map.getPlayer().getPosition().getY()].isShop()){

                    world.addShop();
                    map = world.getCurrentMap();
                    for (KeyListener c : frame.getFrame().getListeners(KeyListener.class)) {
                        frame.getFrame().removeKeyListener(c);
                    }
                    frame.getFrame().addKeyListener(new MoveListener(map));
                }
                if(map.getMap()[map.getPlayer().getPosition().getX()][map.getPlayer().getPosition().getY()].isShopLeave()){
                    world.LeaveShop();
                    map = world.getCurrentMap();
                    for (KeyListener c : frame.getFrame().getListeners(KeyListener.class)) {
                        frame.getFrame().removeKeyListener(c);
                    }
                    frame.getFrame().addKeyListener(new MoveListener(map));
                }

                if (controller.player.isDead(map)) {
                    stop();

                    System.out.println(Ansi.colorize("Le jeu est fini, vous êtes mort...",
                            Attribute.RED_TEXT()));
                    render();
                    quit();
                }
                render();
                Util.playerTurn = true;
            }
        }
    }
}
