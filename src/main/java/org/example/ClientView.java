package org.example;

public interface ClientView {
    void showMessage(String message);
    void updateUserList(String[] users);
    void setClientController(ClientController controller);
    String getSelectedUser();
    String getMessageInput();
    void clearMessageInput();
}