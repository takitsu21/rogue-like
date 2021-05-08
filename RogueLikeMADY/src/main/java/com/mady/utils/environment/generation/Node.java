package com.mady.utils.environment.generation;

import com.mady.utils.Position;

import java.util.Objects;

public class Node {
    private Node parent;
    private Position position;
    private int g;
    private int h;
    private int f;


    public Node(Node parent, Position position) {
        this.parent = parent;
        this.position = position;
        this.f = 0;
        this.h = 0;
        this.g = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, position, g, h, f);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).position.equals(this.position);
        }
        return false;
    }

    public Node getParent() {
        return parent;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
}
