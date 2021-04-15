package com.mady;

import com.mady.utils.KeyboardPressedEnum;
import com.mady.utils.Salle;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class GameController {

    protected Player player;

    /**
     * Initialize Player instance.
     */
    public GameController(Position pos, Salle salle) {
        player = new Player(pos, 100, 5, 1, "@", salle);
    }

    /**
     * Move player position by the provided position.
     *
     * @param pos moving position
     */
    public void movePlayer(Position pos) {
        player.setPos(pos);
    }

    /**
     * Get current position of the player.
     *
     * @return position of player
     */
    public Position getPlayerPosition() {
        return player.getPosition();
    }

    public Player getPlayer() {
        return player;
    }
}
