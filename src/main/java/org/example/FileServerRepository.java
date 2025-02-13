package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileServerRepository implements ServerRepository {
    private static final String LOG_FILE = "chat_history.log";

    @Override
    public String loadHistory() throws IOException {
        if (!Files.exists(Paths.get(LOG_FILE))) {
            return "";
        }
        return new String(Files.readAllBytes(Paths.get(LOG_FILE)));
    }

    @Override
    public void saveMessage(String message) throws IOException {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + System.lineSeparator());
        }
    }
}