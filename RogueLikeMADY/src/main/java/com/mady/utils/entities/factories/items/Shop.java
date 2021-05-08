package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.*;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.monster.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Shop extends Map {
    private final HashMap<AbstractStuffItem, Price> items = new HashMap<>();
    //private final List<AbstractStuffItem> it = new ArrayList<>();
    private final int SHOP_SIZE = 3;
    private Seller henry;
    private Player player;
    private int smallLine = 11;
    private int mediumLine = 19;
    private int largeLine = 27;
    private int hauteurSalle = 13;
    private int posHautsalle = (BASE_HEIGHT - hauteurSalle) / 2;
    private int posSalle = (BASE_WIDTH - smallLine) / 2;
    private Position oldPos;



    public Shop(Player p,Position oldPos) {
        super(1);
        this.player = p;
        this.oldPos = oldPos;

        createShop();
        placeSeller();
        p.setPos(new Position(16, 63));
        super.addPlayerToMap(p);
        placeItems();
        GeneratePortal();


    }
    private void GeneratePortal(){
        super.getMap()[17][63] = new Case("$", CaseType.SHOPLEAVE);
    }



    private void placeItems() {
        //generateItems();
        Position p1 = new Position(posHautsalle + 4, posSalle + (smallLine / 2));
        PaidChest c1 = new PaidChest(p1, player.getLvl(), player.getMultiplicateur());
        addItem(c1,player);
        Position p2 = new Position(posHautsalle + 6, posSalle - 3);
        PaidChest c2 = new PaidChest(p2, player.getLvl(), player.getMultiplicateur());
        addItem(c2,player);
        //c2.setItem(it.get(1));
        Position p3 = new Position(posHautsalle + 6, posSalle + smallLine + 2);
        PaidChest c3 = new PaidChest(p3, player.getLvl(), player.getMultiplicateur());
        addItem(c3,player);
        //c3.setItem(it.get(2));
        super.getMap()[p1.getX()][p1.getY()].setItem(c1);
        super.getMap()[p2.getX()][p2.getY()].setItem(c2);
        super.getMap()[p3.getX()][p3.getY()].setItem(c3);
        placePrice(c1);
        placePrice(c2);
        placePrice(c3);


    }


    private void placePrice(PaidChest c) {
        //Price p = items.get(c.getItem());
        int prix = c.getPrice();
        //c.setPrice(prix);
        int[] repesentation = new int[]{prix / 100, (prix % 100) / 10, prix % 10 };
        Position pos = c.getPosition();
        super.getMap()[pos.getX()-1][pos.getY()-1] = new Case(Ansi.colorize((String.valueOf(repesentation[0])),Attribute.BRIGHT_YELLOW_TEXT()), CaseType.PRICE);
        super.getMap()[pos.getX()-1][pos.getY()] = new Case(Ansi.colorize((String.valueOf(repesentation[1])),Attribute.BRIGHT_YELLOW_TEXT()), CaseType.PRICE);
        super.getMap()[pos.getX()-1][pos.getY()+1] = new Case(Ansi.colorize((String.valueOf(repesentation[2])),Attribute.BRIGHT_YELLOW_TEXT()), CaseType.PRICE);

        //super.getMap()[pos.getX()-1][pos.getY()-1].setRepr(Ansi.colorize((String.valueOf(repesentation[0])),Attribute.BRIGHT_YELLOW_TEXT()));
        //super.getMap()[pos.getX()-1][pos.getY()].setRepr(Ansi.colorize((String.valueOf(repesentation[1])),Attribute.BRIGHT_YELLOW_TEXT()));
        //super.getMap()[pos.getX()-1][pos.getY()+1].setRepr(Ansi.colorize((String.valueOf(repesentation[2])),Attribute.BRIGHT_YELLOW_TEXT()));
    }


    private void placeSeller() {
        Position posSeller = new Position(posHautsalle + 2, posSalle + (smallLine / 2));
        Seller s = new Seller(posSeller);
        super.getMap()[posSeller.getX()][posSeller.getY()].setEntity(s);


    }


    private void createShop() {

//        if (BASE_HEIGHT < hauteurSalle || BASE_WIDTH < largeLine)
//            return false;   //assure que la map soit assé grande pour generer le shop
        int posHautsalle = (BASE_HEIGHT - hauteurSalle) / 2;
        int posSalle = (BASE_WIDTH - smallLine) / 2;
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                if ((j >= posSalle && j < posSalle + smallLine && i >= posHautsalle && i < posHautsalle + 2)
                        || (j >= posSalle - 4 && j < posSalle + smallLine + 4 && i == posHautsalle + 2)
                        || ((j >= posSalle - 8 && j < posSalle + smallLine + 8 && i >= posHautsalle + 3 && i <= posHautsalle + 9))
                        || (j >= posSalle - 4 && j < posSalle + smallLine + 4 && i == posHautsalle + 10)
                        || (j >= posSalle && j < posSalle + smallLine && i >= posHautsalle + 11 && i < posHautsalle + 13)) {
                    super.getMap()[i][j] = new Case(" ", CaseType.SALLE);
                } else {
                    super.getMap()[i][j] = new Case(Ansi.colorize("#", Attribute.BRIGHT_BLACK_BACK(), Attribute.BRIGHT_BLACK_TEXT()));
                }
            }
        }


    }

    public Position getOldPos() {
        return oldPos;
    }

    public void setOldPos(Position oldPos) {
        this.oldPos = oldPos;
    }

//    public void generateItems() {
//        for (int i = 0; i < SHOP_SIZE; i++) {
//            addItem(player);
//        }
//    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getSHOP_SIZE() {
        return SHOP_SIZE;
    }

    public HashMap<AbstractStuffItem, Price> getItems() {
        return items;
    }

    private void addItem(PaidChest chest,Player player) {
        AbstractStuffItem item = chest.selectItem(player);
        Price price = new Price(item);
        chest.setPrice(price.getPrice()+ price.getPrice());
        //it.add(item);
        items.put(item, price);
    }

}