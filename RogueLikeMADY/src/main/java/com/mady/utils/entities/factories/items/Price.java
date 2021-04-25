package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Player;

public class Price {
    private AbstractStuffItem item;
    private int price;

    public Price(AbstractStuffItem item, Player p) {
        this.item = item;
        generatePrice(p);
    }

    public void generatePrice(Player p) {
        // TODO: générer un prix en fonction de l'item
        int newPrice = 0;
        switch(item.getName()){
            case "helmet" :
                newPrice = (int) (((item.getDEF() + item.getATK() + item.getHP() + item.getMP() ) / 4) * (2.0 / 5)) ;
                break;
            case "gauntlet":
                newPrice = (int) ((item.getDEF() + item.getATK() + item.getHP() + item.getMP() ) * (2.0 / 5)) ;
                break;
            case "amulet" :
                newPrice = (int) item.getLUK() ;
                break;
            case "pant":
                newPrice = (int) ((item.getDEF() + item.getATK() + item.getHP() + item.getMP() ) * (2.0 / 5)) ;
                break;
            case "shoes" :
                newPrice = (int) ((item.getDEF() + item.getATK() + item.getHP() + item.getMP() ) * (2.0 / 5)) ;
                break;
            case "weapon":
                newPrice = (int) ((item.getDEF() + item.getATK() + item.getHP() + item.getMP() ) * (2.0 / 5)) ;
                break;
            case "chest" :
                newPrice = (int) ((item.getDEF() + item.getATK() + item.getHP() + item.getMP() ) * (2.0 / 5)) ;
                break;
            default:
                newPrice = 999;
        }
        if(this.price == 0){
            this.price = 1;
        }
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
