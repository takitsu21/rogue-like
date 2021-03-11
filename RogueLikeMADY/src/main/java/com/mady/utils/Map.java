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
                map[i][j] = new Case(" ");
            }
        }

        generateRooms();
        selectLien();
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
        int x = p.getX() > 2 ? p.getX() - 3 : p.getX();
        int y = p.getY() > 2 ? p.getY() - 3 : p.getY();
        lignes = lignes != BASE_HEIGHT ? lignes + 5 : lignes;
        colonnes = colonnes != BASE_WIDTH ? colonnes + 5 : colonnes;

        if (isInside(lignes + x, colonnes + y)) {
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    if (!isInside(i + x, j + y) || !map[i + x][j + y].isMap()) {
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
//            map[s.getPos().getX()][s.getPos().getY()].setRepr(String.valueOf(salles.indexOf(s)));
//            map[salleselect.getPos().getX()][salleselect.getPos().getY()].setRepr(String.valueOf(salles.indexOf(salleselect)));
            System.out.println(String.valueOf(salles.indexOf(s)) + "-" + String.valueOf(salles.indexOf(salleselect)));
            relie(s, salleselect);
            s = salleselect;
            relier.set(salles.indexOf(salleselect), true);
        }
    }


    private void relie(Salle s1, Salle s2) {

        Position pos1= s1.findMiddle();
        Position pos2= s2.findMiddle();
        /*if (s1.getPos().getX() < s2.getPos().getX()) {
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
        map[x1 + s1.getPos().getX()][y1 + s1.getPos().getY()] = new Case("'", CaseType.PATH);
        map[x2 + s2.getPos().getX()][y2 + s2.getPos().getY()] = new Case("'", CaseType.PATH);*/

        AStar aStar = new AStar();
        int[][] res = aStar.search(this, 5, pos1, pos2, s1, s2);
        setupPaths(res);
    }

    private void setupPaths(int[][] solvedPath) {
        for (int i = 0; i < solvedPath.length; i++) {
            for (int j = 0; j < solvedPath[i].length; j++) {
                if (solvedPath[i][j] != -1) {
                    map[i][j].setRepr("'");
                    map[i][j].setCt(CaseType.PATH);
                }
            }
        }
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
