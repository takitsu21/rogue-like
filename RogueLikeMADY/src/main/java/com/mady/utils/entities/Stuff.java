package com.mady.utils.entities;

import java.util.HashMap;
import java.util.Map;

public class Stuff {

    //    private final List<AbstractStuffItem> items = new ArrayList<>(
//            Arrays.asList(helmet, weapon, shoes, pant, chest, amulet, gauntlet)
//    );
//    private final Map<String, AbstractStuffItem> items = Stream.of(new Object[][]{
//            {"helmet", helmet},
//            {"weapon", weapon},
//            {"shoes", shoes},
//            {"pant", pant},
//            {"amulet", amulet},
//            {"gauntlet", gauntlet},
//            {"chest", chest}
//    }).collect(Collectors.toMap(data -> (String) data[0], data -> (AbstractStuffItem) data[1]));
    private final Map<String, AbstractStuffItem> items = new HashMap<>();
    private AbstractStuffItem helmet;
    private AbstractStuffItem weapon;
    private AbstractStuffItem shoes;
    private AbstractStuffItem pant;
    private AbstractStuffItem chest;
    private AbstractStuffItem amulet;
    private AbstractStuffItem gauntlet;

    public AbstractStuffItem getHelmet() {
        return helmet;
    }

    public void setHelmet(AbstractStuffItem helmet) {
        this.helmet = helmet;
        items.put(helmet.getName(), helmet);
    }

    public AbstractStuffItem getWeapon() {
        return weapon;
    }

    public void setWeapon(AbstractStuffItem weapon) {
        this.weapon = weapon;
        items.put(weapon.getName(), weapon);
    }

    public AbstractStuffItem getAmulet() {
        return amulet;
    }

    public void setAmulet(AbstractStuffItem amulet) {
        this.amulet = amulet;
        items.put(amulet.getName(), amulet);
    }

    public AbstractStuffItem getGauntlet() {
        return gauntlet;
    }

    public void setGauntlet(AbstractStuffItem gauntlet) {
        this.gauntlet = gauntlet;
        items.put(gauntlet.getName(), gauntlet);
    }

    public AbstractStuffItem getShoes() {
        return shoes;
    }

    public void setShoes(AbstractStuffItem shoes) {
        this.shoes = shoes;
        items.put(shoes.getName(), shoes);
    }

    public AbstractStuffItem getPant() {
        return pant;
    }

    public void setPant(AbstractStuffItem pant) {
        this.pant = pant;
        items.put(pant.getName(), pant);
    }

    public AbstractStuffItem getChest() {
        return chest;
    }

    public void setChest(AbstractStuffItem chest) {
        this.chest = chest;
        items.put(chest.getName(), chest);
    }

    public Map<String, AbstractStuffItem> getItems() {
        return items;
    }
}
