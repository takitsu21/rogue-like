package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.List;

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

    private void generateRoom(int x, int y) {
        Salle s = new Salle(new Position(x, y));

        for (int i = 0; i < s.getLargeur(); i++) {
            for (int j = 0; j < s.getHauteur(); j++) {
                map[i + y][j + y] = s.getRepresentation()[i][j];
            }
        }
        salles.add(s);
    }

    private void generateRooms() {
        generateRoom(10, 10);
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
