package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame implements ClientView {
    private final JTextArea chatLog = new JTextArea();
    private final JList<String> userList = new JList<>();
    private ClientController controller;

    public ClientGUI() {
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createUserListPanel(), BorderLayout.EAST);
        mainPanel.add(createChatPanel(), BorderLayout.CENTER);
        mainPanel.add(createInputPanel(), BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JScrollPane createUserListPanel() {
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setPreferredSize(new Dimension(120, 0));
        return scrollPane;
    }

    private JScrollPane createChatPanel() {
        chatLog.setEditable(false);
        return new JScrollPane(chatLog);
    }

    private JPanel createInputPanel() {
        JTextField tfMessage = new JTextField();
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(this::handleSendAction);
        tfMessage.addActionListener(this::handleSendAction);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tfMessage, BorderLayout.CENTER);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    private void handleSendAction(ActionEvent e) {
        if (controller != null) {
            controller.onSendButtonClick();
        }
    }

    @Override
    public void showMessage(String message) {
        chatLog.append(message + "\n");
    }

    @Override
    public void updateUserList(String[] users) {
        userList.setListData(users);
    }

    @Override
    public void setClientController(ClientController controller) {
        this.controller = controller;
    }

    @Override
    public String getSelectedUser() {
        return userList.getSelectedValue();
    }

    @Override
    public String getMessageInput() {
        JPanel panel = (JPanel) getContentPane().getComponent(0);
        Container container = (Container) panel.getComponent(2);
        JTextField textField = (JTextField) container.getComponent(0);
        return textField.getText();
    }

    @Override
    public void clearMessageInput() {
        JPanel panel = (JPanel) getContentPane().getComponent(0);
        Container container = (Container) panel.getComponent(2);
        JTextField textField = (JTextField) container.getComponent(0);
        textField.setText("");
    }
}