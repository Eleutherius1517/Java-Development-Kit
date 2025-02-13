package org.example;

public interface ServerView {
    void appendToLog(String message);
    void loadHistory(String history);
}