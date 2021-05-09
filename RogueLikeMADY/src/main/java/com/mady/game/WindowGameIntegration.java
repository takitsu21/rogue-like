package com.mady.game;

import com.github.rjeschke.txtmark.Processor;
import com.mady.utils.listener.HelpListener;

import javax.swing.*;

public class WindowGameIntegration {
    private final JFrame frame;
    private final JLabel label = new JLabel("Focus");


    public WindowGameIntegration() {
        this.frame = new JFrame("MADY RogueLike");
        frame.setSize(280, 125);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label.setText("Stay focused on this window to play.");
        frame.getContentPane().add(label);
        JMenuItem menuItem = new JMenuItem("Aide");
        JMenuBar menuBar = new JMenuBar();


        JMenu help = new JMenu("Plus d'infos");
        help.setMnemonic('F');
        menuItem.addActionListener(new HelpListener());
        help.add(menuItem);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);
        frame.revalidate();

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
