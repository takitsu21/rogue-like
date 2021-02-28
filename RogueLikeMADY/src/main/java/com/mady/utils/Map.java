package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int nbSalles;
    private final Case[][] map;
    private final List<Salle> salles = new ArrayList<>();
    private final int BASE_HEIGHT = 16;
    private final int BASE_WIDTH = 128;

    public static void main(String[] args) {
        Map map = new Map(5);
        map.createMap();
        System.out.println(map.toString());
    }


    public Map(int nbSalles) {
        this.nbSalles = nbSalles;
        this.map = new Case[BASE_HEIGHT][BASE_WIDTH];
    }

    private void createMap() {
        for (int i = 0; i < BASE_HEIGHT; i++) {
            for (int j = 0; j < BASE_WIDTH; j++) {
                map[i][j] = new Case(".");
            }
        }
        generateRooms();
        selectLien();
    }

    public int getBASE_HEIGHT() {
        return BASE_HEIGHT;
    }

    public int getBASE_WIDTH() {
        return BASE_WIDTH;
    }

    public Case[][] getMap() {
        return map;
    }

    public int getNbSalles() {
        return nbSalles;
    }

    public List<Salle> getSalles() {
        return salles;
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

    private boolean isInside(int x, int y) {
        return (x >= 0 && x < BASE_HEIGHT) && (y >= 0 && y < BASE_WIDTH);
    }


    private void generateRooms() {
        Position p = new Position(0, 0);
        for (int i = 0; i < nbSalles; i++) {
            generateRoom(p.getRandomPos(BASE_HEIGHT, BASE_WIDTH));
        }

    }

    private boolean checkFreeArea(Position p, int lignes, int colonnes) {
        boolean result = true;
        int x = p.getX() != 0 ? p.getX() - 1 : p.getX();
        int y = p.getY() != 0 ? p.getY() - 1 : p.getY();
        lignes = lignes != BASE_HEIGHT ? lignes + 2 : lignes;
        colonnes = colonnes != BASE_WIDTH ? colonnes + 2 : colonnes;

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

    private void selectLien() {
        Salle s = salles.get(0);
        ArrayList<Boolean> relier = new ArrayList<>(salles.size());
        for (int i = 0; i < salles.size(); i++) {
            relier.add(false);
        }

        relier.set(0, true);

        while (relier.contains(false)) {
            Double distance = (double) Integer.MAX_VALUE;
            Salle salleselect = s;
            for (Salle s2 : salles) {
                Double distance2 = Math.sqrt(Math.pow(s2.getPos().getX() - s.getPos().getX(), 2)
                        + Math.pow(s2.getPos().getY() - s.getPos().getY(), 2));
                if (!s.equals(s2) && distance2 < distance && !relier.get(salles.indexOf(s2))) {
                    distance = distance2;
                    salleselect = s2;

                }
            }
            map[s.getPos().getX()][s.getPos().getY()].setRepr(String.valueOf(salles.indexOf(s)));
            map[salleselect.getPos().getX()][salleselect.getPos().getY()].setRepr(String.valueOf(salles.indexOf(salleselect)));
            System.out.println(String.valueOf(salles.indexOf(s)) + "-" + String.valueOf(salles.indexOf(salleselect)));
            relie(s, salleselect);
            s = salleselect;
            relier.set(salles.indexOf(salleselect), true);

        }
    }

    private void relie(Salle s1, Salle s2) {

        int x1;
        int y1;
        int x2;
        int y2;
        if (s1.getPos().getX() < s2.getPos().getX()) {
            if (s1.getPos().getY() < s2.getPos().getY()) {
                x1 = s1.getlignes() - 1;
                y1 = s1.getcolonnes() / 2;
                x2 = s2.getlignes() / 2;
                y2 = 0;
                //gauche+bas
            } else {
                x1 = s1.getlignes() / 2;
                y1 = 0;
                x2 = 0;
                y2 = s2.getcolonnes() / 2;

                //gauche+haut
            }
        } else {
            if (s1.getPos().getY() < s2.getPos().getY()) {
                x1 = s1.getlignes() / 2;
                y1 = s1.getcolonnes() - 1;
                x2 = s2.getlignes() - 1;
                y2 = s2.getcolonnes() / 2;

                //droit+bas
            } else {
                x1 = 0;
                y1 = s1.getcolonnes() / 2;
                x2 = s2.getlignes() / 2;
                y2 = s2.getcolonnes() - 1;

                //droit+haut
            }
        }

        //Position pos1=new Position(x1,y1);
        //Position pos2=new Position(x2,y2);
        map[x1 + s1.getPos().getX()][y1 + s1.getPos().getY()] = new Case("*");
        map[x2 + s2.getPos().getX()][y2 + s2.getPos().getY()] = new Case("*");

        createPath(new Position(x1 + s1.getPos().getX(), y1 + s1.getPos().getY()),
                new Position(x2 + s2.getPos().getX(), y2 + s2.getPos().getY()));
    }


    private void createPath(Position src, Position dst) {
        System.out.println("creating path");
        int x = src.getX();
        int y = src.getY();
//        System.out.println(src.equals(dst));
//        System.out.println(src.equals(src));
//        System.out.println(src);
        int acc = 0;
        while (!src.equals(dst)) {
            if (acc == 500) {
                return;
            }
            acc++;
            x = src.getX();
            y = src.getY();
//            System.out.println(src);

//            System.out.println(map[x - 1][y].isFreeCase());
            if (src.getX() > dst.getX() && !map[x - 1][y].isSalle()) {
                src = left(src);
            } else if (src.getX() < dst.getX() && !map[x + 1][y].isSalle()) {
                src = right(src);
            } else if (src.getY() < dst.getY() && !map[x][y + 1].isSalle()) {
                src = down(src);
            } else if (src.getY() > dst.getY() && !map[x][y - 1].isSalle()) {
                src = up(src);
            }

            map[src.getX()][src.getY()].setRepr("*");
//            System.out.println(src);
//            return;
        }
    }

    private Position left(Position src) {
        return new Position(src.getX() - 1, src.getY());
    }

    private Position right(Position src) {
        return new Position(src.getX() + 1, src.getY());
    }

    private Position up(Position src) {
        return new Position(src.getX(), src.getY() - 1);
    }

    private Position down(Position src) {
        return new Position(src.getX(), src.getY() + 1);
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
