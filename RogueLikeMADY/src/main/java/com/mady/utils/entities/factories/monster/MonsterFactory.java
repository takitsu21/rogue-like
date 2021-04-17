package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class MonsterFactory {
    public static final int nbMonsters = 2;
    private static final MonsterFactory instance = new MonsterFactory();

    private MonsterFactory() {
    }

    public static MonsterFactory getInstance() {
        return instance;
    }

    public Monster generate(int id, Position position, Salle salle, Player player) {
        switch (id) {
            case 0:
                return new GoblinArcher(position, salle, player);
            case 1:
                return new OrcWarrior(position, salle, player);
            default:
                return null;
        }
    }

    public Monster generate(String id, Position position, Salle salle, Player player) {
        switch (id) {
            case "goblinArcher":
                return generate(0, position, salle, player);
            case "orcWarrior":
                return generate(1, position, salle, player);
            default:
                return null;
        }
    }
}
