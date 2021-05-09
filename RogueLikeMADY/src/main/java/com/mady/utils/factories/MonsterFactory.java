package com.mady.utils.factories;

import com.mady.utils.Position;
import com.mady.utils.entities.*;
import com.mady.utils.environment.Salle;

public class MonsterFactory {
    public static final int nbMonsters = 5;
    private static final MonsterFactory instance = new MonsterFactory();

    private MonsterFactory() {
    }

    public static MonsterFactory getInstance() {
        return instance;
    }

    public Monster generate(int id, Position position, Salle salle) {
        switch (id) {
            case 0:
                return new GoblinArcher(position, salle);
            case 1:
                return new OrcWarrior(position, salle);
            case 2:
                return new Witch(position, salle);
            case 3:
                return new Troll(position, salle);
            case 4:
                return new DarkDruide(position, salle);


            default:
                return null;
        }
    }

    public Monster generate(String id, Position position, Salle salle) {
        switch (id) {
            case "goblinArcher":
                return generate(0, position, salle);
            case "orcWarrior":
                return generate(1, position, salle);
            case "Sorcière":
                return generate(2, position, salle);
            case "Troll":
                return generate(3, position, salle);
            case "Druide noir":
                return generate(4, position, salle);
            default:
                return null;
        }
    }
}