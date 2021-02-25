package com.mady.utils;

public class Case {
    private String repr;
    private Object item; // objet qui representera l'objet associé a cette case (monstre, coffre etc...)

    public Case(String repr, Object item) {
        this.repr = repr;
        this.item = item;
    }

    public Case(String repr) {
        this(repr, null);
    }

    public Case() {
        this(" ", null);
    }

    public boolean isOccupied() {
        return item != null;
    }

    @Override
    public String toString() {
        return repr;
    }

    public boolean isWall() {
        return repr.equals("#");
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
}
