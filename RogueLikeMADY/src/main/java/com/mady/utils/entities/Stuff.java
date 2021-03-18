package com.mady.utils.entities;

import com.mady.utils.entities.factories.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stuff {

    private static AbstractStuffItem helmet;
    private static AbstractStuffItem weapon;
    private static AbstractStuffItem shoes;
    private static AbstractStuffItem pant;
    private static AbstractStuffItem chest;
    private final List<AbstractStuffItem> items = new ArrayList<>(
            Arrays.asList(helmet, weapon, shoes, pant, chest)
    );

    public AbstractStuffItem getHelmet() {
        return helmet;
    }

    public void setHelmet(AbstractStuffItem helmet) {
        Stuff.helmet = helmet;
    }

    public AbstractStuffItem getWeapon() {
        return weapon;
    }

    public void setWeapon(AbstractStuffItem weapon) {
        Stuff.weapon = weapon;
    }

    public AbstractStuffItem getShoes() {
        return shoes;
    }

    public void setShoes(AbstractStuffItem shoes) {
        Stuff.shoes = shoes;
    }

    public AbstractStuffItem getPant() {
        return pant;
    }

    public void setPant(AbstractStuffItem pant) {
        Stuff.pant = pant;
    }

    public AbstractStuffItem getChest() {
        return chest;
    }

    public void setChest(AbstractStuffItem chest) {
        Stuff.chest = chest;
    }

    public List<AbstractStuffItem> getItems() {
        return items;
    }
}
