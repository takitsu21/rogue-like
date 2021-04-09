package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.AbstractStuffItem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> inventory = new ArrayList<>();

    public Inventory() {
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public boolean isFull() {
        return inventory.size() == 10;
    }

    public boolean addItem(Item i) {
        if (isFull()) {
            return false;
        }
        inventory.add(i);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item i : inventory) {
            AbstractStuffItem it = (AbstractStuffItem) i;
            sb.append(i.getName());
            sb.append(" : ");
            sb.append(it.getAGI());
            sb.append(it.getATK());
            sb.append(it.getHP());
            sb.append(it.getMP());
            sb.append(it.getLUK());

            sb.append("\n");
        }
        return sb.toString();
    }
}
