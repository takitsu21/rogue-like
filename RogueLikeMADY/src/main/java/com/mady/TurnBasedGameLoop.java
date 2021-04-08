package com.mady;

import com.mady.utils.Util;
import com.mady.utils.entities.Entities;

public class TurnBasedGameLoop extends GameLoop{

    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            for (Entities entitie : map.getEntities()) {
                map = entitie.doTurn(map);
            }
            if (controller.player.isDead()) {
                stop();
                System.exit(0);
            }
            System.out.println(status);
            render();
            Util.playerTurn = true;
        }
    }
}
