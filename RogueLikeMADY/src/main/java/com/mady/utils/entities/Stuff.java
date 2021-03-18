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

    public static AbstractStuffItem getHelmet() {
        return helmet;
    }

    public static void setHelmet(AbstractStuffItem helmet) {
        Stuff.helmet = helmet;
    }

    public static AbstractStuffItem getWeapon() {
        return weapon;
    }

    public static void setWeapon(AbstractStuffItem weapon) {
        Stuff.weapon = weapon;
    }

    public static AbstractStuffItem getShoes() {
        return shoes;
    }

    public static void setShoes(AbstractStuffItem shoes) {
        Stuff.shoes = shoes;
    }

    public static AbstractStuffItem getPant() {
        return pant;
    }

    public static void setPant(AbstractStuffItem pant) {
        Stuff.pant = pant;
    }

    public static AbstractStuffItem getChest() {
        return chest;
    }

    public static void setChest(AbstractStuffItem chest) {
        Stuff.chest = chest;
    }

    public List<AbstractStuffItem> getItems() {
        return items;
    }
}
