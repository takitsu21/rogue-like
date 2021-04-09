package com.mady.utils;

import com.mady.utils.entities.AbstractEntities;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.factories.items.Item;

public class Case {
    private String repr;
    private Object item; // objet qui representera l'objet associé a cette case (monstre, coffre etc...)
    private CaseType ct;
    private Entities entity;


    public Case(String repr, Object item, CaseType ct) {
        this.repr = repr;
        this.item = item;
        this.ct = ct;
    }

    public Case(String repr, Object item, CaseType ct, Entities entity) {
        this.repr = repr;
        this.item = item;
        this.ct = ct;
        this.entity = entity;
    }

    public Case(String repr) {
        this(repr, null, CaseType.MAP);
    }

    public Case(CaseType ct) {
        this(" ", null, ct);
    }

    public Case(String repr, CaseType ct) {
        this(repr, null, ct);
    }

    public boolean isOccupied() {
        return item != null || entity != null;
    }

    public boolean isPlayer() {
        //System.out.println(entity instanceof Player);
//        System.out.println(entity.equals(Player));
        return entity instanceof Player;
    }

    @Override
    public String toString() {
        return getRepr();
    }

    public boolean isWall() {
        return CaseType.WALL == ct;
    }

    public boolean isSalle() {
        return CaseType.SALLE == ct;
    }

    public boolean isMap() {
        return CaseType.MAP == ct;
    }

    public String getRepr() {
        return repr;
    }

    public void setRepr(String repr) {
        this.repr = repr;
    }

    public Item getItem() {
        return (Item) item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.repr = item == null ? " " : item.getRepresentation();
    }

    public CaseType getCt() {
        return ct;
    }

    public Entities getEntity() {
        return entity;
    }

    public void setEntity(Entities entity) {
        this.entity = entity;
        repr = entity == null ? " " : entity.getRepr();
    }

    public void setCt(CaseType ct) {
        this.ct = ct;
    }

    public boolean isFreeCase() {
        return !isWall() && !isOccupied();
    }

    public boolean isPath() {
        return CaseType.PATH == ct;
    }
}
