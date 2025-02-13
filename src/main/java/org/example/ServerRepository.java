package org.example;

import java.io.IOException;

public interface ServerRepository {
    String loadHistory() throws IOException;
    void saveMessage(String message) throws IOException;
}