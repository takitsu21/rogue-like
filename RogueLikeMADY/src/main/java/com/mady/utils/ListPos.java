package com.mady.utils;

import com.mady.utils.entities.Position;

import java.util.ArrayList;
import java.util.List;

public class ListPos {
    private List<Position> portes = new ArrayList<>();

    public ListPos(List<Position> portes) {
        this.portes = portes;

    }

    public List<Position> getList() {
        return portes;
    }

    public void setList(List<Position> portes) {
        this.portes = portes;
    }

}
