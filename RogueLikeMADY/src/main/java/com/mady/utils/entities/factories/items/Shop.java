package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Player;

import java.util.HashMap;

public class Shop {
    private final HashMap<AbstractStuffItem, Price> items = new HashMap<>();
    private final int SHOP_SIZE = 3;

    public Shop() {
    }

    public void generateItems(Player player) {
        for (int i = 0; i < SHOP_SIZE; i++) {
            addItem(player);
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getSHOP_SIZE() {
        return SHOP_SIZE;
    }

    public HashMap<AbstractStuffItem, Price> getItems() {
        return items;
    }

    private void addItem(Player player) {
        AbstractStuffItem item = Chest.openChest(player);
        Price price = new Price(item);
        items.put(item, price);
    }

}
