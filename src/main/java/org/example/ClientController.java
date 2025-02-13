package org.example;

import javax.swing.*;

public class ClientController {
    private final ClientView view;
    private final ServerWindow server;

    public ClientController(ClientView view, ServerWindow server) {
        this.view = view;
        this.server = server;
        view.setClientController(this);
        initUsers();
    }

    private void initUsers() {
        String[] defaultUsers = {"Алиса", "Боб", "Чарли", "Диана", "Эвелин"};
        view.updateUserList(defaultUsers);
    }

    public void onSendButtonClick() {
        String user = view.getSelectedUser();
        String message = view.getMessageInput().trim();

        if(user == null || user.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Выберите пользователя из списка!",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(!message.isEmpty()) {
            String formattedMessage = user + ": " + message;
            server.appendToLog(formattedMessage);
            view.showMessage(formattedMessage);
            view.clearMessageInput();
        }
    }
}