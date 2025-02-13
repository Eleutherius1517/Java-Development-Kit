package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private final JTextArea chatLog = new JTextArea();
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

        // Изменяем компоновку для добавления списка пользователей
        getContentPane().add(userListScroll, BorderLayout.EAST);

        // Убираем поле tfLogin
        panelTop.remove(tfLogin);
        setupUserList();

        // Настройка списка пользователей
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setVisibleRowCount(4);

        // Добавляем область чата
        chatLog.setEditable(false);
        JScrollPane chatScroll = new JScrollPane(chatLog);
        add(chatScroll, BorderLayout.CENTER);

        // Обработчики событий
        btnSend.addActionListener(this::sendMessage);
        tfMessage.addActionListener(this::sendMessage);

        setVisible(true);
    }

    private void setupUserList() {
        String[] users = {"Алиса", "Боб", "Чарли", "Диана", "Эвелин"};
        userList.setListData(users);
        userList.setSelectedIndex(0); // Выбираем пользователя по умолчанию
        userList.setBackground(new Color(240, 240, 240));
        userListScroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Выберите пользователя"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
    }
    

    public void sendMessage(ActionEvent e) {
        String message = tfMessage.getText().trim();
        if (message.isEmpty()) return;

        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this,
                    "Выберите пользователя из списка!",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String fullMessage = selectedUser + ": " + message;

        // Добавляем сообщение в клиентский и серверный лог
        chatLog.append(fullMessage + "\n");
        server.appendToLog(fullMessage);

        tfMessage.setText("");
    }
}
