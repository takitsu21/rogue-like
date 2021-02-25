package com.mady.utils.entities.factories.monster;

import com.mady.utils.entities.Position;

public class MonsterFactory {
    private MonsterFactory() {}

    private  static final MonsterFactory instance = new MonsterFactory();

    public static MonsterFactory getInstance() {
        return instance;
    }

    public monster generate(String id, Position position) {
        switch(id) {
            case "golbinArcher":
                return new GoblinArcher(position);
            case "orcWarrior":
                return new OrcWarrior(position);
            default:
                throw new IllegalArgumentException("Unknown monster");
        }
    }
}
