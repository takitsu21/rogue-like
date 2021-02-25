package com.mady.utils;

import com.mady.utils.Position;

public class Salle {
    private final int largeur;
    private final int hauteur;
    private int monster_number;
    private int object_number;
    private Case[][] representation;


    Salle(int largeur , int hauteur, int monster_number, int object_number){
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.monster_number = monster_number;
        this.object_number = object_number;
        representation = new Case[largeur][hauteur];
        addMonster();
        addItem();
    }

    public Case[][] getRepresentation() {
        return representation;
    }

    private Position getFreePos (){
        int x = (int) (Math.random() * largeur);
        int y = (int) (Math.random() * largeur);
        while (!representation[x][y].isOccupied() && !representation[x][y].isWall()){
             x = (int) (Math.random() * largeur);
             y = (int) (Math.random() * largeur);
        }
        return new Position(x,y);
    }

    private void addMonster(){
        for (int i=0; i<monster_number; i++){
            Position pos = getFreePos();

            representation[pos.getX()][pos.getY()].setItem(new Object()); //monster

        }
    }

    private void addItem(){
        for (int i=0; i<object_number; i++){
            Position pos = getFreePos();

            representation[pos.getX()][pos.getY()].setItem(new Object()); //item

        }
    }


    private void createSalle(int l, int h){
        for(int j = 0 ; j < largeur; j++){
            representation[j][0] = new Case("#",null);
        }
        for(int i = 1 ; i < hauteur-1; i++){
            representation[0][i] = new Case("#",null);
            for(int k = 1; k < largeur-1; k ++){
                representation[k][i] = new Case(" ",null);
            }
            representation[largeur][i] = new Case("#",null);
        }
        for(int j = 0 ; j < largeur; j++){
            representation[j][hauteur] = new Case("#",null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < largeur; i++){
            for(int j = 0 ; j < hauteur; j ++ ){
                sb.append(representation[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
