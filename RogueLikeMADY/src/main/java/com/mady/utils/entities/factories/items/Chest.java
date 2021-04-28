package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.*;

public class Chest extends AbstractItem {
    private int price;

    private AbstractStuffItem item;

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
    public static AbstractStuffItem openChest(Player player) {
        double lvl = player.getLvl();
        int randomLUK = Util.r.nextInt((int) player.getLUK()) + 1;
        double baseMultiplicator = player.getMultiplicateur();
        double baseStat = 5;
        double newRandomLUK = randomLUK * lvl * baseMultiplicator;
        double randomATK = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomDEF = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomAGI = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomHP = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
        double randomMP = Util.r.nextInt((int) (baseStat * (lvl * baseMultiplicator) + randomLUK)) + randomLUK;
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

    @Override
    public void act(Player player) {

    }

    public void setItem(AbstractStuffItem item) {
        this.item = item;
    }

    public AbstractStuffItem getItem() {
        return item;
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
