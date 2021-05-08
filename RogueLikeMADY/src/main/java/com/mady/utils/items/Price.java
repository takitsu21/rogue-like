package com.mady.utils.items;

import com.mady.utils.Util;
import com.mady.utils.items.stuff.AbstractStuffItem;

public class Price {
    private AbstractStuffItem item;
    private int price;

    public Price(AbstractStuffItem item) {
        this.item = item;
        generatePrice(this.item);
    }

    public void generatePrice(AbstractStuffItem item) {
        // TODO: générer un prix en fonction de l'item
        int newPrice = 0;
        switch (item.getName()) {
            case "helmet":
                newPrice = (int) (((item.getDEF() + item.getATK() + item.getHP() + item.getMP()) / 4) * (0.1));
                break;
            case "gauntlet":
            case "pant":
            case "shoes":
            case "weapon":
            case "chest":
                newPrice = (int) ((item.getDEF() + item.getATK() + item.getHP() + item.getMP()) * (0.2));
                break;
            case "amulet":
                newPrice = item.getLUK() * (int) Math.log(item.getLUK());
                break;
            default:
                newPrice = 999;
        }
        this.price = newPrice == 0 ? Util.r.nextInt(6) + 1 : newPrice;
        if (price > 999) {
            this.price = 999;
        }

    }

    public AbstractStuffItem getItem() {
        return item;
    }

    public void setItem(AbstractStuffItem item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.valueOf(price);
    }
}
