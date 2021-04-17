package com.mady.utils.entities.factories.items;

import com.mady.utils.Salle;
import com.mady.utils.entities.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {
    ItemFactory f = ItemFactory.getInstance();
    Salle s = new Salle(new Position(1, 1));
    Position p = new Position(1, 1);


    @Test
    void generate() {
        assertTrue(f.generate(p, "potion_vie") instanceof PotionVie);
        assertTrue(f.generate(p, "potion_force") instanceof PotionForce);
        assertTrue(f.generate(p, "poison_vie") instanceof PoisonVie);
        assertTrue(f.generate(p, "poison_force") instanceof PoisonForce);
        assertTrue(f.generate(p, "chest") instanceof Chest);
        assertFalse(f.generate(p, "chest") instanceof PotionVie);
        assertNull(f.generate(p, "pomme de terre "));
        assertNull(f.generate(p, "goblin Archer"));
        assertNotNull(f.generate(p, "potion_vie"));

    }
}