package com.mady.utils.entities.factories.items;

import com.mady.utils.Salle;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {
    ItemFactory f = ItemFactory.getInstance();
    Salle s = new Salle(new Position(1, 1));
    Position p = new Position(1, 1);
    Player player=new Player(new Position(0, 0), 0, 0, 0, "@", new Salle(0, 0, 0, 0, new Position(0, 0)));


    @Test
    void generate() {
        assertTrue(f.generate(p, "potion_vie", player) instanceof PotionVie);
        assertTrue(f.generate(p, "potion_force",player) instanceof PotionForce);
        assertTrue(f.generate(p, "poison_vie",player) instanceof PoisonVie);
        assertTrue(f.generate(p, "poison_force", player) instanceof PoisonForce);
        assertTrue(f.generate(p, "chest",player) instanceof Chest);
        assertFalse(f.generate(p, "chest",player) instanceof PotionVie);
        assertNull(f.generate(p, "pomme de terre ",player));
        assertNull(f.generate(p, "goblin Archer",player));
        assertNotNull(f.generate(p, "potion_vie",player));

    }
}