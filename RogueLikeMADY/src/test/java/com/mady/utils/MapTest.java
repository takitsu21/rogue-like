package com.mady.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    private Map map1;
    private Map map2;

    @BeforeEach
    void setUp() {
        map1 = new Map(5, 32, 16);
        map2 = new Map(6, 16, 64);
    }

    @Test
    void isInside() {
        assertTrue(map1.isInside(15, 15));
        assertFalse(map1.isInside(5, 17));
        assertTrue(map2.isInside(1, 57));
        assertFalse(map2.isInside(-1, 65));

        assertFalse(map1.isInside(32, 16));
        assertFalse(map2.isInside(16, 64));
    }

    @Test
    void nbSallesCreated() {
        assertEquals(5, map1.getNbSalles());
        assertNotEquals(4, map1.getNbSalles());
        assertEquals(6, map2.getNbSalles());
        assertNotEquals(8, map2.getNbSalles());

        assertNotEquals(Integer.MAX_VALUE, map2.getNbSalles());
        assertNotEquals(Integer.MIN_VALUE, map2.getNbSalles());
    }
}