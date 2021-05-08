package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.items.stuff.AbstractStuffItem;
import com.mady.utils.items.stuff.Stuff;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<AbstractStuffItem> inventory = new ArrayList<>();
    private final int MAX_SIZE = 10;
    private int selectedItem = 0;
    private Stuff s;

    public Inventory(Stuff s) {
        this.s = s;
    }

    public List<AbstractStuffItem> getInventory() {
        return inventory;
    }

    public boolean isFull() {
        return inventory.size() == MAX_SIZE;
    }

    public boolean addItem(AbstractStuffItem i) {
        if (isFull()) {
            Util.currentAction.append(Ansi.colorize("Votre inventaire est plein!\n", Attribute.RED_TEXT()));
            return false;
        }
        inventory.add(i);
        return true;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     *
     * @return the representation of yuor stuff.
     * each part of your armor plus weapon and amulet got their places.
     * An equiped item will have a '*' in his place.
     */

}
