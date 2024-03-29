package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.items.stuff.AbstractStuffItem;

public class PaidChest extends Chest {
    private AbstractStuffItem item;

    public PaidChest(Position position, int lvl, double multiplicateur, int price) {
        super(position, lvl, multiplicateur, price);
    }

    public PaidChest(Position position, int lvl, double multiplicateur) {
        super(position, lvl, multiplicateur);
    }

    public AbstractStuffItem selectItem(Player player) {
        setItem(super.openChest(player));
        return getItem();
    }


    @Override
    public AbstractStuffItem openChest(Player player) {
        return getItem();
    }


    public AbstractStuffItem getItem() {
        return item;
    }

    public void setItem(AbstractStuffItem item) {
        this.item = item;
    }

    public void showItem() {
        Util.currentAction.append(Ansi.colorize(String.format("Dans ce coffre il y a <%s> : %s\n",
                getItem().getName(), getItem().toString()), Attribute.MAGENTA_TEXT()));
    }
}
