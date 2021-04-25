package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Case;
import com.mady.utils.Frame;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.monster.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Shop extends Map {
    private final HashMap<AbstractStuffItem, Price> items = new HashMap<>();
    private final List<AbstractStuffItem> it = new ArrayList<>();
    private final int SHOP_SIZE = 3;
    private Seller henry;
    private Player player;
    private int smallLine = 11;
    private int mediumLine = 19;
    private int largeLine = 27;
    private int hauteurSalle = 13;
    private int posHautsalle = (BASE_HEIGHT - hauteurSalle) / 2;
    private int posSalle = (BASE_WIDTH - smallLine) / 2;

    public static void main(String[] args) {
        Position p = new Position(16, 63);
        Shop s = new Shop(new Player(p, 100, 5, 1, "@", new Salle(p)));

        System.out.println(s);
    }

    public Shop(Player p) {
        super(1);
        this.player = p;
        createShop();
        placeSeller();
        p.setPos(new Position(16, 63));
        super.addPlayerToMap(p);
        placeItems();


    }

    private void placeItems() {
        generateItems();
        Position p1 = new Position(posHautsalle + 4, posSalle + (smallLine / 2));
        Chest c1 = new Chest(p1, player.getLvl(), player.getMultiplicateur());
        c1.setItem(it.get(0));
        Position p2 = new Position(posHautsalle + 6, posSalle - 3);
        Chest c2 = new Chest(p2, player.getLvl(), player.getMultiplicateur());
        c2.setItem(it.get(1));
        Position p3 = new Position(posHautsalle + 6, posSalle + smallLine + 2);
        Chest c3 = new Chest(p3, player.getLvl(), player.getMultiplicateur());
        c3.setItem(it.get(2));
        super.getMap()[p1.getX()][p1.getY()].setItem(c1);
        super.getMap()[p2.getX()][p2.getY()].setItem(c2);
        super.getMap()[p3.getX()][p3.getY()].setItem(c3);
        placePrice(c1);
        placePrice(c2);
        placePrice(c3);


    }

    private void placePrice(Chest c) {
        Price p = items.get(c.getItem());
        int prix = p.getPrice();
        int[] repesentation = new int[]{prix / 100, (prix % 100) / 10, prix % 10 };
        Position pos = c.getPosition();
        super.getMap()[pos.getX()-1][pos.getY()-1].setRepr(String.valueOf(repesentation[0]));
        super.getMap()[pos.getX()-1][pos.getY()].setRepr(String.valueOf(repesentation[1]));
        super.getMap()[pos.getX()-1][pos.getY()+1].setRepr(String.valueOf(repesentation[2]));
    }


    private void placeSeller() {
        Position posSeller = new Position(posHautsalle + 3, posSalle + (smallLine / 2));
        Seller s = new Seller(posSeller);
        super.getMap()[posHautsalle + 2][posSalle + (smallLine / 2)].setEntity(s);


    }


    private void createShop() {

//        if (BASE_HEIGHT < hauteurSalle || BASE_WIDTH < largeLine)
//            return false;   //assure que la map soit assé grande pour generer le shop
        int posHautsalle = (BASE_HEIGHT - hauteurSalle) / 2;
        int posSalle = (BASE_WIDTH - smallLine) / 2;
        System.out.println(posHautsalle);
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                if ((j >= posSalle && j < posSalle + smallLine && i >= posHautsalle && i < posHautsalle + 2)
                        || (j >= posSalle - 4 && j < posSalle + smallLine + 4 && i == posHautsalle + 2)
                        || ((j >= posSalle - 8 && j < posSalle + smallLine + 8 && i >= posHautsalle + 3 && i <= posHautsalle + 9))
                        || (j >= posSalle - 4 && j < posSalle + smallLine + 4 && i == posHautsalle + 10)
                        || (j >= posSalle && j < posSalle + smallLine && i >= posHautsalle + 11 && i < posHautsalle + 13)) {
                    super.getMap()[i][j] = new Case(" ");
                } else {
                    super.getMap()[i][j] = new Case(Ansi.colorize("#", Attribute.BRIGHT_BLACK_BACK(), Attribute.BRIGHT_BLACK_TEXT()));
                }
            }
        }


    }

    public void generateItems() {
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
        Price price = new Price(item,player);
        it.add(item);
        items.put(item, price);
    }

}