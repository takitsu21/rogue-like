package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.AbstractStuffItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public int getSelectedItem() {
        return selectedItem;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int acc = 0;
        for (Item i : inventory) {
            AbstractStuffItem it = (AbstractStuffItem) i;
//            StringBuilder sbTmp = new StringBuilder();
            if (acc == selectedItem) {
                sb.append('[');
            }

            sb.append('<').append(i.getName().substring(0, 1).toUpperCase()).append(i.getName().substring(1))
                    .append('>')
                    .append(" : ")
                    .append("AGI ")
                    .append(it.getAGI())
                    .append(" ATK ")
                    .append(it.getATK())
                    .append(" HP ")
                    .append(it.getHP())
                    .append(" MP")
                    .append(it.getMP())
                    .append(" LUK")
                    .append(it.getLUK());
            if (acc == selectedItem) {
                sb.append(']');
//                sb.append(Ansi.colorize(sbTmp.toString(), Attribute.RED_TEXT()));
//                System.out.println();
            }
//            else {
//                sb.append(sbTmp);
//            }

            sb.append("\n");
            acc++;
        }
        return sb.toString();
    }
}
