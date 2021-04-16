package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.*;

public class Chest extends AbstractItem {

    private AbstractStuffItem item;

    /**
     *
     * @param position du coffre.
     */
    public Chest(Position position) {
        super("C", position, 0, 0, "Coffre au trésor", false, true);
    }

    @Override
    public void act(Player player) {

    }

    @Override
    public String getRepresentation() {
        return Ansi.colorize("C", Attribute.MAGENTA_TEXT());
    }

    /**
     *
     * @param player
     * @return AbstractStuffItem
     */
    public AbstractStuffItem openChest(Player player) {
        double lvl = player.getLvl();
        int randomLUK = Util.r.nextInt((int)player.getLUK());
        double baseMultiplicator = 2;
        double baseStat = 5;
        double newRandomLUK = randomLUK * lvl * baseMultiplicator;
        double randomATK = Util.r.nextInt((int)(baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomDEF = Util.r.nextInt((int)(baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomAGI = Util.r.nextInt((int)(baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomHP = Util.r.nextInt((int)(baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomMP = Util.r.nextInt((int)(baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        switch (Util.r.nextInt(7)) {
            case 0:
                return new Amulet(newRandomLUK);
            case 1:
                return new ChestPlate(randomHP, randomMP, randomATK, randomDEF, randomAGI, 0);
            case 2:
                return new Gauntlet(randomHP, randomMP, randomATK, randomDEF, randomAGI, 0);
            case 3:
                return new Helmet(randomHP, randomMP, randomATK, randomDEF, randomAGI, 0);
            case 4:
                return new Pants(randomHP, randomMP, randomATK, randomDEF, randomAGI, 0);
            case 5:
                return new Shoes(randomHP, randomMP, randomATK, randomDEF, randomAGI, 0);
            case 6:
                return new Weapon(randomHP, randomMP, randomATK, randomDEF, randomAGI, 0);
            default:
                return null;
        }
    }
}
