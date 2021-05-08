package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.items.stuff.*;

public class Chest extends AbstractItem {
    private int price;

    /**
     * @param position du coffre.
     */
    public Chest(Position position, int lvl, double multiplicateur, int price) {
        super("C", position, 0, 0, "Coffre au trésor", false, true, lvl, multiplicateur);
        this.price = price;
    }

    public Chest(Position position, int lvl, double multiplicateur) {
        this(position, lvl, multiplicateur, 0);
    }

    /**
     * @param player joueur a partir duquel vont etre changer les statistiques de l'item
     * @return AbstractStuffItem Item créer quand on ouvre le coffre.
     */
    public AbstractStuffItem openChest(Player player) {
        int lvl = player.getLvl();
        int randomLUK = Util.r.nextInt((int) player.getLUK()) + 1;
        Double baseMultiplicator = player.getMultiplicateur();
        int baseStat = 5;
        int newRandomLUK = Math.min((int) (randomLUK * lvl * baseMultiplicator), 100);
        int randomATK = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        int randomDEF = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        int randomAGI = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        int randomHP = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        int randomMP = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        switch (Util.r.nextInt(7)) {
            case 0:
                return new Amulet((int) newRandomLUK);
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

    @Override
    public void act(Player player) {

    }


    @Override
    public String getRepr() {
        return Ansi.colorize("C", Attribute.MAGENTA_TEXT());
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
