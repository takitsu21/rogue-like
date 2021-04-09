package com.mady;

import com.mady.utils.Util;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.listener.MoveListener;

public class TurnBasedGameLoop extends GameLoop{

    @Override
    protected void processGameLoop() {

        while (isGameRunning()) {
            processInput();

            for (Entities entitie : currentMap.getEntities()) {
                currentMap = entitie.doTurn(currentMap);
            }

            if(currentMap.getMap()[currentMap.getPlayer().getPosition().getX()][currentMap.getPlayer().getPosition().getY()].isPortal()){
                System.out.println("1");
                world.addMap();
                System.out.println("2");
                Position position=world.getCurrentMap().randomPosPlayerInSalle(world.getCurrentMap().chooseSalle());
                currentMap.getPlayer().setPos(position);
                world.getCurrentMap().addPlayerToMap(currentMap.getPlayer());
                currentMap=world.getCurrentMap();
                currentMap.getFrame().getFrame().addKeyListener(new MoveListener(currentMap));
                System.out.println("3");
            }

                if (controller.player.isDead()) {
                    stop();
                    System.exit(0);
                }
            System.out.println("4");
            render();
            System.out.println("5");
            Util.playerTurn = true;
        }
    }
}
