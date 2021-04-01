package com.mady;

import com.mady.utils.entities.Entities;

public class TurnBasedGameLoop extends GameLoop{

    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            for (Entities entitie : map.getEntities()) {
                map = entitie.doTurn(map);
            }
            render();
        }
    }
}
