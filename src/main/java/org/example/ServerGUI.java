package org.example;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements ServerView {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;

    private final JTextArea log = new JTextArea();
    private final JButton btnStart = new JButton("Server Start");
    private final JButton btnStop = new JButton("Server Stop");

    public ServerGUI() {
        setTitle("Chat Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);

        // Layout
        setLayout(new BorderLayout());
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        // Panel with buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void appendToLog(String message) {
        log.append(message + "\n");
    }

    @Override
    public void loadHistory(String history) {
        log.setText(history);
    }

    public void setStartButtonAction(Runnable action) {
        btnStart.addActionListener(e -> action.run());
    }

    public void setStopButtonAction(Runnable action) {
        btnStop.addActionListener(e -> action.run());
    }
}