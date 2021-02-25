package com.mady.utils;

import com.mady.utils.entities.Position;

public class Salle {
    private final int largeur;
    private final int hauteur;
    private final Position pos;
    private final int monsterNumber;
    private final int objectNumber;
    private Case[][] representation;


    public Salle(int largeur,
                 int hauteur,
                 int monsterNumber,
                 int objectNumber,
                 Position pos) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.monsterNumber = monsterNumber;
        this.objectNumber = objectNumber;
        this.pos = pos;
        representation = new Case[largeur][hauteur];
        createSalle(largeur, hauteur);
        addMonster();
        addItem();
    }

    public Salle(int largeur, int hauteur, Position pos) {
        this(largeur, hauteur, 0, 0, pos);
    }

    public Salle(Position pos) {
        this(10, 15, 0, 0, pos);
    }

    public Case[][] getRepresentation() {
        return representation;
    }

    private Position getFreePos() {
        int x = (int) (Math.random() * largeur);
        int y = (int) (Math.random() * largeur);
        while (!representation[x][y].isOccupied() && !representation[x][y].isWall()) {
            x = (int) (Math.random() * largeur);
            y = (int) (Math.random() * largeur);
        }
        return new Position(x, y);
    }


    private void addMonster() {
        for (int i = 0; i < monsterNumber; i++) {
            Position pos = getFreePos();
            representation[pos.getX()][pos.getY()].setItem(new Object());
        }
    }

    private void addItem() {
        for (int i = 0; i < objectNumber; i++) {
            Position pos = getFreePos();
            representation[pos.getX()][pos.getY()].setItem(new Object());
        }
    }

    private void createSalle(int l, int h) {
        for (int j = 0; j < largeur; j++) {
            representation[j][0] = new Case("#", null);
        }
        for (int i = 1; i < hauteur - 1; i++) {
            representation[0][i] = new Case("#", null);
            for (int k = 1; k < largeur - 1; k++) {
                representation[k][i] = new Case(" ", null);
            }
            representation[largeur - 1][i] = new Case("#", null);
        }
        for (int j = 0; j < largeur; j++) {
            representation[j][hauteur - 1] = new Case("#", null);
        }
    }

    public Position getPos() {
        return pos;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getmonsterNumber() {
        return monsterNumber;
    }

    public int getobjectNumber() {
        return objectNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                sb.append(representation[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
