package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Position;

public class ItemFactory {
    private static final ItemFactory INSTANCE = new ItemFactory();

    private ItemFactory() {
    }

    public static ItemFactory getInstance() {
        return INSTANCE;
    }

    public Item generate(Position position, String id) {
        switch (id) {
            case "potion_vie":
                return new PotionVie(position);
            case "potion_force":
                return new PotionForce(position);
            case "poison_vie":
                return new PoisonVie(position);
            case "poison_force":
                return new PoisonForce(position);
            case "chest":
                return new Chest(position);
            default:
                return null;
        }
    }
}
