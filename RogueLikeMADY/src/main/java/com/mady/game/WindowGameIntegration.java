package com.mady.game;

import javax.swing.*;

public class WindowGameIntegration {
    private final JFrame frame;
    private JLabel label = new JLabel("Focus");

    public WindowGameIntegration() {
        this.frame = new JFrame("MADY RogueLike");
        frame.setSize(280, 125);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label.setText("Stay focused on this window to play.");
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
}
