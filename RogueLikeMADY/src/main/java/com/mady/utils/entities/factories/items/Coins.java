package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class Coins extends AbstractItem {
    public Coins(Position position) {
        super("*", position, 0, 0, "MADY Coins", true, false, 0, 1);
    }

    @Override
    public void act(Player player) {
        int val = Util.r.nextInt(6) +1;
        player.setCoins(player.getCoins() + val );
        Util.currentAction.append(Ansi.colorize(String.format("Vous obtenez %d MADY coin!\n",val), Attribute.BRIGHT_YELLOW_TEXT()));
    }
}
