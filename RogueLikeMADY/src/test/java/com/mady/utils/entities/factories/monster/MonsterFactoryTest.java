package com.mady.utils.entities.factories.monster;

import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.GoblinArcher;
import com.mady.utils.entities.OrcWarrior;
import com.mady.utils.entities.Player;
import com.mady.utils.environment.Salle;
import com.mady.utils.factories.MonsterFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterFactoryTest {
    MonsterFactory f = MonsterFactory.getInstance();
    Salle s = new Salle(new Position(1, 1));
    Position p = new Position(1, 1);
    Player player = new Player(new Position(0, 0), 0, 0, 0, "@", new Salle(0, 0, new Position(0, 0)));

    @Test
    void generate() {
        assertTrue(f.generate(0, p, s, player) instanceof GoblinArcher);
        assertTrue(f.generate(1, p, s, player) instanceof OrcWarrior);
        assertNotNull(f.generate(Util.r.nextInt(MonsterFactory.nbMonsters), p, s, player));
        assertNull(f.generate(55, p, s, player));

        assertTrue(f.generate("goblinArcher", p, s, player) instanceof GoblinArcher);
        assertTrue(f.generate("orcWarrior", p, s, player) instanceof OrcWarrior);
        assertNull(f.generate("pomme de terre", p, s, player));
        assertFalse(f.generate(0, p, s, player) instanceof OrcWarrior);


    }
}