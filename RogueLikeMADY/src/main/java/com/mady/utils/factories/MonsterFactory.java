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

    public Monster generate(int id, Position position, Salle salle, Player player) {
        AbstractMonster monster;
        switch (id) {
            case 0:
                monster = new GoblinArcher(position, salle);
                break;
            case 1:
                monster = new OrcWarrior(position, salle);
                break;
            case 2:
                monster = new Witch(position, salle);
                break;
            case 3:
                monster = new Troll(position, salle);
                break;
            case 4:
                monster = new DarkDruide(position, salle);
                break;

            default:
                monster = null;
        }
        if (monster != null) {
            monster.setLvl(player.getLvl());
        }
        return monster;
    }

    public Monster generate(String id, Position position, Salle salle, Player player) {
        switch (id) {
            case "goblinArcher":
                return generate(0, position, salle, player);
            case "orcWarrior":
                return generate(1, position, salle, player);
            case "Sorcière":
                return generate(2, position, salle, player);
            case "Troll":
                return generate(3, position, salle, player);
            case "Druide noir":
                return generate(4, position, salle, player);
            default:
                return null;
        }
    }
}
