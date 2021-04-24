package com.mady.utils.entities.factories.items;

import com.mady.utils.Case;
import com.mady.utils.Frame;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

import java.util.HashMap;

public class Shop extends Map {
    private final HashMap<AbstractStuffItem, Price> items = new HashMap<>();
    private final int SHOP_SIZE = 3;

    public static void main(String[] args) {
        Position p = new Position(0,0);
        Shop s = new Shop(new Player( p,100, 5, 1, "@",new Salle(p)));

        System.out.println(s);
    }

    public Shop(Player p) {
        super(1);
        createShop();
        super.addPlayerToMap(p);

    }
    private void createShop(){
        int smallLine = 10;
        int mediumLine = 18;
        int largeLine = 26;
        int hauteurSalle = 13;
        int posHautsalle = (BASE_HEIGHT - hauteurSalle) /2 ;
        int posSalle = (BASE_WIDTH - smallLine) / 2 ;
        System.out.println(posSalle);
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                if((j >= posSalle-4 && j < posSalle + smallLine+4 && i == 5 )
                        || (j >= posSalle && j < posSalle + smallLine && i >= 3 && i < 5)
                        || ((j >= posSalle-8 && j < posSalle + smallLine+8 && i >= 6 && i <= 12 ) )
                        ||(j >= posSalle-4 && j < posSalle + smallLine+4 && i == 13)
                ||(j >= posSalle && j < posSalle + smallLine && i >= 14 && i < 16) ){
                    super.getMap()[i][j] = new Case(" ");
                }
//                else if{
//                    super.getMap()[i][j] = new Case(" ");
//                }
                else{
                super.getMap()[i][j] = new Case("#"); }
            }
        }



    }

    public void generateItems(Player player) {
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
        Price price = new Price(item);
        items.put(item, price);
    }

}
