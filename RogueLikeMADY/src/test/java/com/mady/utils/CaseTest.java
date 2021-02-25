package com.mady.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {
    private Case cWall;
    private Case cEmpty;
    private Case cItemNotEmpty;
    private Case cConstructor;

    @BeforeEach
    void setUp() {
        cWall = new Case("#", null);
        cEmpty = new Case();
        cItemNotEmpty = new Case("C", new String(""));
        cConstructor = new Case(".");
    }

    @Test
    void isOccupied() {
        assertFalse(cWall.isOccupied());
        assertFalse(cEmpty.isOccupied());
        assertTrue(cItemNotEmpty.isOccupied());
        assertFalse(cConstructor.isOccupied());
    }

    @Test
    void testToString() {
        assertEquals("#", cWall.toString());
        assertEquals(" ", cEmpty.toString());
        assertEquals("C", cItemNotEmpty.toString());
        assertEquals(".", cConstructor.toString());
    }

    @Test
    void isWall() {
        assertTrue(cWall.isWall());
        assertFalse(cEmpty.isWall());
    }
}