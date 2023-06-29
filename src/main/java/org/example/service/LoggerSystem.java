package org.example.service;

import org.example.enums.AccessType;
import org.example.model.Log;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface LoggerSystem {
    void createLog(LocalDateTime localDateTime, User creator, String text, AccessType type);

    String deleteLog(Log log, User user);

    List<Log> getLogs(User creator);

    List<Log> getDeletedLogs(User creator);
}
