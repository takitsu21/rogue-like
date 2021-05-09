package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.items.stuff.AbstractStuffItem;

public class Elixir extends PaidChest {
    private final String name;
    private final int MAX_POTION = 5;

    public Elixir(Position position,String name) {
        super(position,1, 1, 2);
        this.name = name;
    }

    @Override
    public AbstractStuffItem openChest(Player player) {
        if ( getPrice() > player.getCoins()) {
            //c.setItem(null);
            Util.currentAction.append(Ansi.colorize("Vous n'avez pas assez de MADY coins pour ouvrir le coffre.\n"));
        }
        else{
        if(name.equals("Vie") && player.getElixirVie() < MAX_POTION){
            player.setElixirVie(player.getElixirVie()+1);
            player.setCoins(player.getCoins()-getPrice());
            Util.currentAction.append(Ansi.colorize(String.format("Vous achetez une potion de %s\n",name), Attribute.MAGENTA_TEXT()));
        }
        else  if(name.equals("Mana") && player.getElixirMana() < MAX_POTION){
            player.setElixirMana(player.getElixirMana()+1);
            player.setCoins(player.getCoins()-getPrice());
            Util.currentAction.append(Ansi.colorize(String.format("Vous achetez une potion de %s\n",name), Attribute.MAGENTA_TEXT()));
        }
        else if(player.getElixirMana() +1 > MAX_POTION || player.getElixirVie() +1 > MAX_POTION){
            Util.currentAction.append(Ansi.colorize(String.format("Vous achetez trop de d'elixirs de %s",name), Attribute.MAGENTA_TEXT()));
        }
        }
        return null;
    }

    @Override
    public void showItem() {
        Util.currentAction.append(Ansi.colorize(String.format("Dans ce coffre il y a des potions de %s au prix de %d MadyCoin\n",name,getPrice()), Attribute.MAGENTA_TEXT()));
    }
}
