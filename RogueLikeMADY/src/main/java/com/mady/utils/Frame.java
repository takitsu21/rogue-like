package com.mady.utils;

import javax.swing.*;

public class Frame {
    private JFrame frame;

    public Frame() {
        this.frame = new JFrame("MADY");

        frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }


}
