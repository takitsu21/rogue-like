package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterFactoryTest {
    MonsterFactory f = MonsterFactory.getInstance() ;
    Salle s = new Salle(new Position(1,1));
    Position p = new Position(1,1);


    @Test
    void generate() {
        assertTrue(f.generate(0,p,s) instanceof GoblinArcher);
        assertTrue(f.generate(1,p,s) instanceof OrcWarrior);
        assertTrue(f.generate(  Util.r.nextInt(MonsterFactory.nbMonsters),p,s) instanceof Monster);
        assertTrue(f.generate(55,p,s) == null);

        assertTrue(f.generate("goblinArcher",p,s) instanceof GoblinArcher);
        assertTrue(f.generate("orcWarrior",p,s) instanceof OrcWarrior);
        assertTrue(f.generate("pomme de terre",p,s) == null);
        assertFalse(f.generate(0,p,s) instanceof OrcWarrior);


    }
}