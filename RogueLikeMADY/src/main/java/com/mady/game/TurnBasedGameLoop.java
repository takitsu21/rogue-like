package com.mady.game;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Entities;
import com.mady.utils.enums.KeyboardPressedEnum;
import com.mady.utils.environment.Salle;
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
        Welcome();
        while (isGameRunning()) {


            processInput();
            while (isGameAttackMenu()) {

                processInput();
                if (isGameRunning()) break;
                render();
                Util.playerTurn = true;
            }
            if (isGamePaused()) {
                while (isGamePaused()) {
                    processInput();
                    if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                        status = GameStatus.RUNNING;
                    }
                    render();
                    Util.playerTurn = true;
                }
                Welcome();
            } else if (isGameRunning()) {
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
                    Util.refreshKeyListener(windowGameIntegration, map);
                }
                if (map.getMap()[map.getPlayer().getPosition().getX()][map.getPlayer().getPosition().getY()].isShop()) {

                    world.addShop();
                    map = world.getCurrentMap();
                    Util.refreshKeyListener(windowGameIntegration, map);
                } else if (map.getMap()[map.getPlayer().getPosition().getX()][map.getPlayer().getPosition().getY()].isShopLeave()) {
                    world.LeaveShop();
                    map = world.getCurrentMap();
                    Util.refreshKeyListener(windowGameIntegration, map);
                }

                if (controller.player.isDead()) {
                    stop();
                    System.out.println(Ansi.colorize("Le jeu est fini, vous êtes mort...",
                            Attribute.RED_TEXT()));
                    quit();
                }
                render();
                Util.playerTurn = true;
            }
        }
    }

    private void Welcome() {
        while (isWelcomeScreen()) {
            processInput();
            if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                status = GameStatus.RUNNING;
            } else if (Util.keyPressed == KeyboardPressedEnum.HELP) {
                Util.printHELP();
            } else {
                render();
            }
            Util.playerTurn = true;
        }
        render();
        Util.playerTurn = true;
    }
}
