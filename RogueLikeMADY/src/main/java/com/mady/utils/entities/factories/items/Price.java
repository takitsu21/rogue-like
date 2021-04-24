package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.AbstractStuffItem;

public class Price {
    private AbstractStuffItem item;
    private int price;

    public Price(AbstractStuffItem item) {
        this.item = item;
        generatePrice();
    }

    public void generatePrice() {
        // TODO: générer un prix en fonction de l'item
        int newPrice = 0;
        this.price = newPrice;
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
