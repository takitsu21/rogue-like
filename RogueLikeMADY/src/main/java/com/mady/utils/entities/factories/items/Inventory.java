package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.AbstractStuffItem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> inventory = new ArrayList<>();
    private final int MAX_SIZE = 10;
    private int selectedItem = 0;

    public Inventory() {
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public boolean isFull() {
        return inventory.size() == MAX_SIZE;
    }

    public boolean addItem(Item i) {
        if (isFull()) {
            return false;
        }
        inventory.add(i);
        return true;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int acc = 0;
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
            acc++;
        }
        return sb.toString();
    }
}
