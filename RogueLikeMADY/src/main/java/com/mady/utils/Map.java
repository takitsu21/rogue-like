package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private final int nbSalles;
    private final Case[][] map;
    private List<Salle> salles = new ArrayList<>();
    private final int BASE_HEIGHT = 16;
    private final int BASE_WIDTH = 128;

    public static void main(String[] args) {
        Map map = new Map(1);
        map.createMap();
//        for (int i = 0; i < 3; i ++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.printf("#");
//            }
//            System.out.println();
//        }
        Position pos = map.salles.get(0).getPos();
//        map.map[pos.getX()][pos.getY()] = new Case("@");
//        System.out.println(Arrays.deepToString(map.salles.get(0)));

//        System.out.println();
        System.out.println(map.toString());
    }


    public Map(int nbSalles) {
        this.nbSalles = nbSalles;
        this.map = new Case[BASE_HEIGHT][BASE_WIDTH];
    }

    private void createMap() {
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
        System.out.printf("posx %d posy %d, (%d, %d)\n",
                s.getPos().getX(), s.getPos().getY(), p.getX(), p.getY());
        while (!checkFreeArea(p, s.getlignes(), s.getcolonnes())) {
            p = p.getRandomPos(BASE_HEIGHT, BASE_WIDTH);
            s = new Salle(p);
            x = p.getX();
            y = p.getY();
            System.out.printf("posx %d posy %d, (%d, %d)\n",
                    s.getPos().getX(), s.getPos().getY(), s.getlignes(), s.getcolonnes());
        }
        System.out.println("outside checkfree");
        for (int i = 0; i < s.getlignes(); i++) {
            for (int j = 0; j < s.getcolonnes(); j++) {
                System.out.printf("(%d, %d)\n", i + x, j + y);
                map[i + x][j + y] = s.getRepresentation()[i][j];

            }
        }
        System.out.println(Arrays.deepToString(s.getRepresentation()));
        salles.add(s);


    }

    private boolean isInside(int x, int y) {
        return (x >= 0 && x < BASE_HEIGHT) && (y >= 0 && y < BASE_WIDTH);
    }


    private void generateRooms() {
        Position p = new Position(0, 0);
        for (int i = 0; i < 40; i++) {
            generateRoom(p.getRandomPos(BASE_HEIGHT, BASE_WIDTH));
        }

    }

    private boolean checkFreeArea(Position p, int lignes, int colonnes) {
        boolean result = true;
        int x = p.getX();
        int y = p.getY();
        System.out.printf("%d + %d = %d, %d + %d = %d\n", lignes, x, lignes + x, colonnes, y, colonnes + y);
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
