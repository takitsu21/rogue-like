package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int nbSalles;
    private final Case[][] map;
    private final List<Salle> salles = new ArrayList<>();
    private final int BASE_HEIGHT;
    private final int BASE_WIDTH;

    public static void main(String[] args) {
        Map map = new Map(5, 16, 128);
        map.createMap();
        System.out.println(map.toString());
    }


    public Map(int nbSalles, int BASE_HEIGHT, int BASE_WIDTH) {
        this.nbSalles = nbSalles;
        this.BASE_HEIGHT = BASE_HEIGHT;
        this.BASE_WIDTH = BASE_WIDTH;
        this.map = new Case[BASE_HEIGHT][BASE_WIDTH];
    }

    public Map(int nbSalles) {
        this(nbSalles, 16, 128);
    }

    public void createMap() {
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                map[i][j] = new Case();
            }
        }
        generateRooms();
    }

    private void generateRoom(Position p) {
        int x = p.getX();
        int y = p.getY();
        Salle s = new Salle(p);
        while (!checkFreeArea(p, s.getlignes(), s.getcolonnes())) {
            p = p.getRandomPos(BASE_HEIGHT, BASE_WIDTH);
            s = new Salle(p);
            x = p.getX();
            y = p.getY();
        }
        for (int i = 0; i < s.getlignes(); i++) {
            for (int j = 0; j < s.getcolonnes(); j++) {
                map[i + x][j + y] = s.getRepresentation()[i][j];

            }
        }
        salles.add(s);


    }

    public boolean isInside(int x, int y) {
        return (x >= 0 && x < BASE_HEIGHT)
                && (y >= 0 && y < BASE_WIDTH);
    }


    private void generateRooms() {
        Position p = new Position(0, 0);
        for (int i = 0; i < nbSalles; i++) {
            generateRoom(p.getRandomPos(BASE_HEIGHT, BASE_WIDTH));
        }

    }

    private boolean checkFreeArea(Position p, int lignes, int colonnes) {
        boolean result = true;
        int x = p.getX();
        int y = p.getY();
        if (isInside(lignes + x, colonnes + y)) {
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    if (!isInside(i + x, j + y) || map[i + x][j + y].isOccupied()
                            || map[i + x][j + y].isWall()) {
                        result = false;
                        break;
                    }
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    public int getNbSalles() {
        return nbSalles;
    }

    public Case[][] getMap() {
        return map;
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public int getBASE_HEIGHT() {
        return BASE_HEIGHT;
    }

    public int getBASE_WIDTH() {
        return BASE_WIDTH;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                sb.append(map[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
