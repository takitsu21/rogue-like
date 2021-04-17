package com.mady.utils;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {
    private Case cWall;
    private Case cSalle;
    private Case cEmpty;
    private Case cPlayer;
    private Case cMap;
    private Case cPath;
    private Case cPortal;
    private Case cItemNotEmpty;
    private Case cConstructor;


    @BeforeEach
    void setUp() {
        cWall = new Case("#", null, CaseType.WALL);
        cSalle = new Case(" ", null, CaseType.SALLE);
        cEmpty = new Case(CaseType.SALLE);
        cItemNotEmpty = new Case("C", new String(""), CaseType.SALLE);
        cConstructor = new Case(".");
        cPlayer = new Case("@", new Player(new Position(0, 0), 0, 0, 0, "@", new Salle(0, 0, 0, 0, new Position(0, 0))));
        cPath = new Case("P", null, CaseType.PATH);
        cPortal = new Case("§", null, CaseType.PORTAL);
        cMap = new Case(" ", null, CaseType.MAP);
    }

    @Test
    void isOccupied() {
        assertFalse(cSalle.isOccupied());
        assertFalse(cWall.isOccupied());
        assertFalse(cEmpty.isOccupied());
        assertTrue(cItemNotEmpty.isOccupied());
        assertFalse(cConstructor.isOccupied());
        assertTrue(cPlayer.isOccupied());
        assertFalse(cPath.isOccupied());
        assertFalse(cPortal.isOccupied());
        assertFalse(cMap.isOccupied());
    }

    @Test
    void testToString() {
        assertEquals(" ", cSalle.toString());
        assertEquals("#", cWall.toString());
        assertEquals(" ", cEmpty.toString());
        assertEquals("C", cItemNotEmpty.toString());
        assertEquals(".", cConstructor.toString());
        assertEquals("@", cPlayer.toString());
        assertEquals("P", cPath.toString());
        assertEquals("§", cPortal.toString());
        assertEquals(" ", cMap.toString());
    }

    @Test
    void isWall() {
        assertFalse(cSalle.isWall());
        assertTrue(cWall.isWall());
        assertFalse(cEmpty.isWall());
        assertFalse(cPlayer.isWall());
        assertFalse(cPath.isWall());
        assertFalse(cPortal.isWall());
        assertFalse(cMap.isWall());

    }

    @Test
    void isSalle() {
        assertFalse(cWall.isSalle());
        assertTrue(cEmpty.isSalle());
        assertTrue(cSalle.isSalle());
        assertFalse(cPlayer.isSalle());
        assertFalse(cPath.isSalle());
        assertFalse(cPortal.isSalle());
        assertFalse(cMap.isSalle());
    }

    @Test
    void isPlayer() {
        assertFalse(cWall.isPlayer());
        assertFalse(cEmpty.isPlayer());
        assertFalse(cSalle.isPlayer());
        assertTrue(cPlayer.isPlayer());
        assertFalse(cPath.isPlayer());
        assertFalse(cPortal.isPlayer());
        assertFalse(cMap.isPlayer());
    }

    @Test
    void isMap() {
        assertFalse(cWall.isMap());
        assertFalse(cEmpty.isMap());
        assertFalse(cSalle.isMap());
        assertTrue(cPlayer.isMap());
        assertFalse(cPath.isMap());
        assertFalse(cPortal.isMap());
        assertTrue(cMap.isMap());
    }

    @Test
    void isPath() {
        assertFalse(cWall.isPath());
        assertFalse(cEmpty.isPath());
        assertFalse(cSalle.isPath());
        assertFalse(cPlayer.isPath());
        assertTrue(cPath.isPath());
        assertFalse(cPortal.isPath());
        assertFalse(cMap.isPath());
    }

    @Test
    void isPortal() {
        assertFalse(cWall.isPortal());
        assertFalse(cEmpty.isPortal());
        assertFalse(cSalle.isPortal());
        assertFalse(cPlayer.isPortal());
        assertFalse(cPath.isPortal());
        assertTrue(cPortal.isPortal());
        assertFalse(cMap.isPortal());
    }

    @Test
    void isFreeCase() {
        assertFalse(cWall.isFreeCase());
        assertTrue(cEmpty.isFreeCase());
        assertTrue(cSalle.isFreeCase());
        assertFalse(cPlayer.isFreeCase());
        assertTrue(cPath.isFreeCase());
        assertTrue(cPortal.isFreeCase());
        assertTrue(cMap.isFreeCase());
    }


}