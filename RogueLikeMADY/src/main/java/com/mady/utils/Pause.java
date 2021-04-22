package com.mady.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pause {
    private int selection;
    private final List<String> liste = new ArrayList<>(Arrays.asList("Resume","Restart","Quit"));

    public Pause() {
        selection = 0;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public int getSelection() {
        return selection;
    }

    public List<String> getListe() {
        return liste;
    }
}
