package com.mady;

import com.mady.utils.KeyboardPressedEnum;
import com.mady.utils.Util;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.listener.MoveListener;

public class TurnBasedGameLoop extends GameLoop{

    @Override
    protected void processGameLoop() {
        if (status == GameStatus.STARTING) {
            status = GameStatus.RUNNING;
            render();
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
            }
            else {
                for (Entities entitie : map.getEntities()) {
                    map = entitie.doTurn(map);
                 }

                if(map.getMap()[map.getPlayer().getPosition().getX()][map.getPlayer().getPosition().getY()].isPortal()){
                    world.addMap();
                    Position position=world.getCurrentMap().randomPosPlayerInSalle(world.getCurrentMap().chooseSalle());
                    map.getPlayer().setPos(position);
                    world.getCurrentMap().addPlayerToMap(map.getPlayer());
                    map=world.getCurrentMap();
                    map.getFrame().getFrame().addKeyListener(new MoveListener(map));
                }

                if (controller.player.isDead()) {
                    stop();
                    System.exit(0);
                }
                render();
                Util.playerTurn = true;
            }
        }
    }
}
