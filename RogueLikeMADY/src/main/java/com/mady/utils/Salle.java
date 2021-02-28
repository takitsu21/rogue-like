package com.mady.utils;

import com.mady.utils.entities.Position;

public class Salle {
    private final int lignes;
    private final int colonnes;
    private final Position pos;
    private final int monsterNumber;
    private final int objectNumber;
    private Case[][] representation;


    public Salle(int lignes,
                 int colonnes,
                 int monsterNumber,
                 int objectNumber,
                 Position pos) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.monsterNumber = monsterNumber;
        this.objectNumber = objectNumber;
        this.pos = pos;
        representation = new Case[lignes][colonnes];
        createSalle();
//        addMonster();
//        addItem();
    }

    public Salle(int lignes, int colonnes, Position pos) {
        this(lignes, colonnes, 0, 0, pos);
    }

    public Salle(Position pos) {
        this(Util.r.nextInt(15 - 3) + 3,
                Util.r.nextInt(15 - 3) + 3,
                0, 0, pos);
    }

    public Case[][] getRepresentation() {
        return representation;
    }

    private Position getFreePos() {
        int x = Util.r.nextInt(lignes);
        int y = Util.r.nextInt(colonnes);
        while (!representation[x][y].isOccupied() && !representation[x][y].isWall()) {
            x = Util.r.nextInt(lignes);
            y = Util.r.nextInt(colonnes);
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

    private void createSalle() {
        for (int j = 0; j < lignes; j++) {
            representation[j][0] = new Case("#", null, CaseType.WALL);
        }
        for (int i = 1; i < colonnes; i++) {

            representation[0][i] = new Case("#", null, CaseType.WALL);
            for (int k = 1; k < lignes; k++) {
                representation[k][i] = new Case(" ", null,CaseType.SALLE);
            }
            representation[lignes - 1][i] = new Case("#", null, CaseType.WALL);
        }
        for (int j = 0; j < lignes; j++) {
            representation[j][colonnes-1] = new Case("#", null, CaseType.WALL);
        }
    }

    public Position getPos() {
        return pos;
    }

    public int getlignes() {
        return lignes;
    }

    public int getcolonnes() {
        return colonnes;
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
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                sb.append(representation[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
