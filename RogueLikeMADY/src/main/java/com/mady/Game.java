package com.mady;

import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class Game {
    public static void main(String[] args) {
        Map map = new Map(5, 30, 64);
        map.createMap();
        generateStaticMap(map);
        traceChemin(map);
        System.out.println(map.toString());
    }



    private static Salle[] generateStaticRooms(){
        Salle s1 = new Salle(5 , 5 , new Position(12,10));
        Salle s2 = new Salle(5 , 5,  new Position(2,30));
        Salle s3 = new Salle(5 , 5,  new Position(20,5));
        Salle s4 = new Salle(5 , 5, new Position(10,24));
        return new Salle[]{s1,s2,s3,s4};
    }

    static void traceChemin(Map m){
        for(int i = 30; i > 26; i-- ){
            m.getMap()[4][i].setRepr("*");
        }
        for(int j = 4 ; j < 11; j++){
            m.getMap()[j][26].setRepr("*");
        }
        for(int x = 24; x > 13 ; x-- ){
            m.getMap()[13][x].setRepr("*");
        }
        for(int i =16 ; i < 23; i++  ){
            m.getMap()[i][12].setRepr("*");
        }
        for(int i = 12; i > 8 ; i--){
            m.getMap()[22][i].setRepr("*");
        }
    }

    static void generateStaticMap(Map m){


    for(Salle s : generateStaticRooms()) {
        int x = s.getPos().getX();
        int y = s.getPos().getY();
        for (int i = 0; i < s.getlignes(); i++) {
            for (int j = 0; j < s.getcolonnes(); j++) {
                m.getMap()[i + x][j + y] = s.getRepresentation()[i][j];

            }
        }
        m.getSalles().add(s);
    }

    }
}
