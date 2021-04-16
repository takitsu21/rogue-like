package com.mady.utils;

import com.mady.utils.entities.Deplacement;
import com.diogonunes.jcolor.Ansi;
import java.util.Random;

public class Util {
    public static final Random r = new Random();
    public static volatile boolean playerTurn = true;
    public static volatile KeyboardPressedEnum keyPressed = KeyboardPressedEnum.NONE;

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
    public static String getRandomItem(){
        int randomIt= Util.r.nextInt(5);
        switch (randomIt) {
            case 0:
                return "potion_vie";
            case 1:
                return "potion_force";
            case 2:
                return "poison_vie";
            case 3:
                return "poison_force";
            default:
                return "chest";
        }
    }



    public static String filler(int i ){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < i ; j++){
            sb.append(" ");

        }
        return sb.toString();
    }
}
