package com.mady.utils;

import com.mady.utils.environment.Salle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalleTest {

    private Salle s1;
    private Salle s2;
    private Salle s3;

    @BeforeEach
    void setUp() {
        s1 = new Salle(5, 6, new Position(1, 1));
        s2 = new Salle(6, 10, new Position(20, 1));
        s3 = new Salle(4, 4, new Position(1, 30));
    }


    @Test
    void inSalle() {
        assertTrue(s1.inSalle(new Position(1, 5)));
        assertTrue(s1.inSalle(new Position(0, 0)));

        assertFalse(s1.inSalle(new Position(66, 66)));
        assertFalse(s1.inSalle(new Position(7, 7)));
        assertTrue(s2.inSalle(s2.findMiddle()));
        assertTrue(s2.inSalle(s2.getPos()));
        assertFalse(s2.inSalle(new Position(0, 0)));
        assertFalse(s2.inSalle(new Position(-1, -9)));
    }

    @Test
    void isCorner() {
        assertFalse(s1.isCorner(s1.findMiddle()));
        assertTrue(s2.isCorner(s2.getPos().incrementPos(new Position(s2.getlignes(), s2.getcolonnes()))));

    }

    @Test
    void findMiddle() {
        assertEquals(s1.findMiddle(),
                new Position(s1.getPos().getX() + s1.getlignes() / 2, s1.getPos().getY() + s1.getcolonnes() / 2));
        assertNotEquals(s1.findMiddle(), s1.getPos());
    }
}
