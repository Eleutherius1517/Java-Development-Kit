package org.example;

public class Main {
    public static void main(String[] args) {
        // Создаем GUI сервера
        ServerView serverView = new ServerGUI();

        // Создаем репозиторий
        ServerRepository serverRepository = new FileServerRepository();

        // Создаем GUI клиента
        ClientGUI clientGUI = new ClientGUI();

        // Создаем контроллер клиента
        ClientController clientController = new ClientController(clientGUI, null);

        // Создаем контроллер сервера
        ServerController serverController = new ServerController(serverView, serverRepository, clientController);

        // Передаем серверный контроллер в клиентский контроллер
        clientController.setServerController(serverController);

        // Запускаем сервер
        serverController.startServer();
    }
}