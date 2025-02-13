package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerController {
    private static final DateTimeFormatter DT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final ServerView view;
    private final ServerRepository repository;
    private final ClientController clientController; // Ссылка на клиентский контроллер
    private boolean isServerWorking;

    public ServerController(ServerView view, ServerRepository repository, ClientController clientController) {
        this.view = view;
        this.repository = repository;
        this.clientController = clientController;
        this.isServerWorking = false;

        loadHistory();

        if (view instanceof ServerGUI serverGUI) {
            serverGUI.setStartButtonAction(this::startServer);
            serverGUI.setStopButtonAction(this::stopServer);
        }
    }

    public void startServer() {
        if (!isServerWorking) {
            isServerWorking = true;
            String message = "Сервер подключен.";
            appendMessage(message);
            clientController.onSystemMessage(message); // Отправляем сообщение клиенту
        }
    }

    public void stopServer() {
        if (isServerWorking) {
            isServerWorking = false;
            String message = "Сервер отключен.";
            appendMessage(message);
            clientController.onSystemMessage(message); // Отправляем сообщение клиенту
        }
    }

    public void appendMessage(String message) {
        if (isServerWorking || message.contains("Сервер")) { // Всегда показываем статус сервера
            String timestampedMsg = "[" + LocalDateTime.now().format(DT_FORMATTER) + "] " + message;
            view.appendToLog(timestampedMsg);

            try {
                repository.saveMessage(timestampedMsg);
            } catch (Exception e) {
                System.err.println("Ошибка записи в лог-файл: " + e.getMessage());
            }
        }
    }

    private void loadHistory() {
        try {
            String history = repository.loadHistory();
            view.loadHistory(history);
        } catch (Exception e) {
            System.err.println("Ошибка чтения лог-файла: " + e.getMessage());
        }
    }
}