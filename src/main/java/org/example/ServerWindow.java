package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    private static final String LOG_FILE = "chat_history.log";
    private static final DateTimeFormatter DT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final JButton btnStart = new JButton("Start Server");
    private final JButton btnStop = new JButton("Stop Server");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;
    public ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Server stopped " + isServerWorking + "\n");
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                System.out.println("Server started " + isServerWorking + "\n");
            }
        });

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        add(buttonPanel, BorderLayout.NORTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y,WIDTH,HEIGHT);
        setResizable(false);
        setTitle("Chat Server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1,2));
        add(btnStart);
        add(btnStop);
        loadHistoryFromFile();

        setVisible(true);
    }
    // Метод для добавления сообщений в лог
    public void appendToLog(String message) {
        String timestampedMsg = "[" + LocalDateTime.now().format(DT_FORMATTER) + "] " + message;

        // Добавляем в GUI
        log.append(timestampedMsg + "\n");

        // Записываем в файл
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(timestampedMsg + System.lineSeparator());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка записи в лог-файл: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadHistoryFromFile() {
        File logFile = new File(LOG_FILE);
        if (!logFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            StringBuilder history = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                history.append(line).append("\n");
            }
            log.setText(history.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка чтения лог-файла: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
