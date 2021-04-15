package com.mady;

import com.mady.utils.KeyboardPressedEnum;
import com.mady.utils.Util;
import com.mady.utils.entities.Entities;

public class TurnBasedGameLoop extends GameLoop{

    @Override
    protected void processGameLoop() {
        if (status == GameStatus.STARTING) {
            status = GameStatus.RUNNING;
            render();
        }
        while (isGameRunning()) {

            processInput();
            System.out.println(status);

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
