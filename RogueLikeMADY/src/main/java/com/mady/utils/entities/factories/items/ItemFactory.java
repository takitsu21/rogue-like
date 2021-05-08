package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class ItemFactory {
    private static final ItemFactory INSTANCE = new ItemFactory();

    private ItemFactory() {
    }

    public static ItemFactory getInstance() {
        return INSTANCE;
    }

    public Item generate(Position position, String id, Player player) {
        switch (id) {
            case "potion_vie":
                return new PotionVie(position, player.getLvl(), player.getMultiplicateur());
            case "potion_force":
                return new PotionMana(position, player.getLvl(), player.getMultiplicateur());
            case "poison_vie":
                return new PoisonVie(position, player.getLvl(), player.getMultiplicateur());
            case "poison_force":
                return new PoisonMana(position, player.getLvl(), player.getMultiplicateur());
            case "chest":
                return new Chest(position, player.getLvl(), player.getMultiplicateur());
            case "coin":
                return new Coins(position);
            default:
                return null;
        }
    }
}
