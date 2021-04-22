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
                return new Boss(position, salle);
            case 5:
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
            case "Boss":
                return generate(4, position, salle);
            case "Druide noir":
                return generate(5, position, salle);
            default:
                return null;
        }
    }
}
