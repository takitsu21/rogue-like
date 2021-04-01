package com.mady.utils;

import com.mady.utils.entities.Deplacement;

import java.util.Random;

public class Util {
    public static final Random r = new Random();
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
}
