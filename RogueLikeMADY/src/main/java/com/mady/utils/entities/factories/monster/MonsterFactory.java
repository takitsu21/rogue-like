package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Position;

public class MonsterFactory {
    private MonsterFactory() {}

    public static final int nbMonsters = 2;

    private  static final MonsterFactory instance = new MonsterFactory();

    public static MonsterFactory getInstance() {
        return instance;
    }

    public Monster generate(int id, Position position, Salle salle) {
        switch(id) {
            case 0:
                return new GoblinArcher(position, salle);
            case 1:
                return new OrcWarrior(position, salle);
            default:
                throw new IllegalArgumentException("Unknown monster");
        }
    }

    public Monster generate(String id, Position position, Salle salle) {
        switch(id) {
            case "goblinArcher":
                return new GoblinArcher(position, salle);
            case "orcWarrior":
                return new OrcWarrior(position, salle);
            default:
                throw new IllegalArgumentException("Unknown monster");
        }
    }
}
