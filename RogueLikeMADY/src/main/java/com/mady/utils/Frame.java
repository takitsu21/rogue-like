package com.mady.utils;

import com.mady.utils.listener.MoveListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicReference;

public class Frame {
    private final JFrame frame;
    private JLabel label = new JLabel("Focus");

    public Frame() {
        this.frame = new JFrame("MADY RogueLike");
        frame.setSize(250, 125);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    public JLabel getLabel() {
        return label;
    }

    public void test() {
        System.out.println("qsdqs");
    }
}
