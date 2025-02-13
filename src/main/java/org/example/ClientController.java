package org.example;

import javax.swing.*;

public class ClientController {
    private final ClientView view;
    private ServerController serverController;

    public ClientController(ClientView view, ServerController serverController) {
        this.view = view;
        this.serverController = serverController;
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

        if (user == null || user.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Выберите пользователя из списка!",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!message.isEmpty()) {
            String formattedMessage = user + ": " + message;
            serverController.appendMessage(formattedMessage); // Отправляем сообщение на сервер
            view.showMessage(formattedMessage);               // Отображаем сообщение у клиента
            view.clearMessageInput();                        // Очищаем поле ввода
        }
    }

    public void onSystemMessage(String message) {
        view.showMessage(message); // Отображаем системное сообщение в клиентском чате
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}