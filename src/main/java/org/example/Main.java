package org.example;

public class Main {
    public static void main(String[] args) {
        ServerWindow server = new ServerWindow();
        ClientGUI clientGUI = new ClientGUI();
        ClientController controller = new ClientController(clientGUI, server);
    }
}

