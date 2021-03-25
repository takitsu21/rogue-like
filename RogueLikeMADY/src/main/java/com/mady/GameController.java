package com.mady;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class GameController {

    protected Player player;

    /**
     * Initialize Player instance.
     */
    public GameController(Position pos) {
        player = new Player(pos, 100, 5, 1, "@");
    }

    /**
     * Move bullet position by the provided offset.
     *
     * @param pos moving offset
     */
    public void movePlayer(Position pos) {
        player.setPos(pos);
    }

    /**
     * Get current position of the bullet.
     *
     * @return position of player
     */
    public Position getPlayerPosition() {
        return player.getPosition();
    }

}
