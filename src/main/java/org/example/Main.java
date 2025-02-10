package org.example;

public class Main {
    public static void main(String[] args) {

        ServerWindow server = new ServerWindow();
        ClientGUI client = new ClientGUI(server);
    }
}

