package com.mady.utils;

import com.diogonunes.jcolor.Attribute;
import com.mady.utils.entities.Deplacement;

import java.util.Random;

public class Util {
    public static final Random r = new Random();
    public static volatile boolean playerTurn = true;
    public static volatile KeyboardPressedEnum keyPressed = KeyboardPressedEnum.NONE;
    public static volatile StringBuilder currentAction = new StringBuilder();

    public static Deplacement randomDirection() {
        int randomDirection = Util.r.nextInt(4);
        switch (randomDirection) {
            case 0:
                return Deplacement.BAS;
            case 1:
                return Deplacement.HAUT;
            case 2:
                return Deplacement.GAUCHE;
            default:
                return Deplacement.DROITE;
        }
    }

    public static String getRandomItem() {
        int randomIt = Util.r.nextInt(15);
        if(randomIt < 4) {
        return "potion_vie";
        }
        if(randomIt == 4 ){
            return "poison_vie";}
        if(randomIt > 4 && randomIt < 9) {
            return "potion_force";
        }
        if(randomIt == 9) {
            return "poison_force";
        }
        else{
            return "chest";}
    }


    public static String filler(int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append(" ");

        }
        return sb.toString();
    }
}
