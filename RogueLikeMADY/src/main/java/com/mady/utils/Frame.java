package com.mady.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicReference;

public class Frame {
    private final JFrame frame;

    public Frame() {
        this.frame = new JFrame("MADY RogueLike");
        frame.setSize(250, 125);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Focus");
        label.setText("Stay focused on this window to play.");
        label.setForeground(Color.RED);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().add(label);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    public JFrame getFrame() {
        return frame;
    }


}
