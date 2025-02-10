package org.example;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private ServerWindow server;
    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Eleutherius");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();
    private final JScrollPane userListScroll = new JScrollPane(userList);

    public ClientGUI(ServerWindow server){
        this.server = server;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH,HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);
        setVisible(true);

        btnSend.addActionListener(e -> sendMessage());
        tfMessage.addActionListener(e -> sendMessage());

        // Настройка списка пользователей
        setupUserList();

        // Изменяем компоновку для добавления списка пользователей
        getContentPane().add(userListScroll, BorderLayout.EAST);
    }
    private void sendMessage() {
        String message = tfMessage.getText().trim();
        if (!message.isEmpty() && server != null) {
            String login = tfLogin.getText();
            server.appendToLog(login + ": " + message);
            tfMessage.setText("");
        }
    }
    private void setupUserList() {
        // Заполняем список тестовыми данными
        String[] users = {"Алиса", "Боб", "Чарли", "Диана", "Эвелин"};
        userList.setListData(users);

        // Настройка внешнего вида
        userList.setBackground(new Color(240, 240, 240));
        userListScroll.setPreferredSize(new Dimension(120, 0));
        userListScroll.setBorder(BorderFactory.createTitledBorder("Online Users"));
    }
}
