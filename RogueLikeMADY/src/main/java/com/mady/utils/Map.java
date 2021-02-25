package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private final int nbSalles;
    private final Case[][] map;
    private List<Salle> salles = new ArrayList<>();
    private final int BASE_HEIGHT = 64;
    private final int BASE_WIDTH = 64;

    public static void main(String[] args) {
        Map map = new Map(1);
        map.createMap();
        Position pos = map.salles.get(0).getPos();
        map.map[pos.getX()][pos.getY()] = new Case("@");
//        System.out.println(Arrays.deepToString(map.salles.get(0)));

        System.out.println();
        System.out.println(map.toString());
    }



    public Map(int nbSalles) {
        this.nbSalles = nbSalles;
        this.map = new Case[BASE_WIDTH][BASE_HEIGHT];
    }

    private void createMap() {
        for (int i = 0; i < BASE_WIDTH; i++) {
            for (int j = 0; j < BASE_HEIGHT; j++) {
                map[i][j] = new Case(".");
            }
        }
        generateRooms();
    }

    private void generateRoom(Position p) {
         int x = p.getX();
         int y = p.getY();
        Salle s = new Salle(p);
        while (! checkFreeArea(p, s.getLargeur(), s.getHauteur())){
            s = new Salle(p);
        }
            for (int i = 0; i < s.getLargeur(); i++) {
                for (int j = 0; j < s.getHauteur(); j++) {
                    map[i + x][j + y] = s.getRepresentation()[i][j];
                }
            }
            salles.add(s);


    }

    private void generateRooms() {
        Position p = new Position(0,0);
        for(int i = 0; i < 3 ; i ++){
            generateRoom(p.getRandomPos(BASE_WIDTH,BASE_HEIGHT));
        }
       
    }

    private boolean checkFreeArea(Position p, int largeur, int hauteur) {
        boolean result = true;
        int x = p.getX();
        int y = p.getY();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                if (map[i + x][j + y].isOccupied()) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BASE_WIDTH; i++) {
            for (int j = 0; j < BASE_HEIGHT; j++) {
                sb.append(map[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
