package com.mady.utils;

public class Case {
    private String repr;
    private Object item; // objet qui representera l'objet associé a cette case (monstre, coffre etc...)
    private CaseType ct;


    public Case(String repr, Object item, CaseType ct) {
        this.repr = repr;
        this.item = item;
        this.ct = ct;
    }

    public Case(String repr) {
        this(repr, null, CaseType.MAP);
    }

    public Case(CaseType ct) {
        this(" ", null, ct);
    }

    public boolean isOccupied() {
        return item != null;
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

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public CaseType getCt() {
        return ct;
    }

    public void setCt(CaseType ct) {
        this.ct = ct;
    }

    public boolean isFreeCase() {
        return !(isWall() && isOccupied());
    }
}
