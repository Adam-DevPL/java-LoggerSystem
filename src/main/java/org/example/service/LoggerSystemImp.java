package org.example.service;

import org.example.enums.AccessType;
import org.example.model.Log;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LoggerSystemImp implements LoggerSystem {

    private final List<Log> logs;
    private final List<Log> deletedLogs;

    public LoggerSystemImp() {
        logs = new ArrayList<>();
        deletedLogs = new ArrayList<>();
    }

    @Override
    public void createLog(LocalDateTime localDateTime, User creator, String text, AccessType type) {
        Log log = new Log(localDateTime, creator, text, type);
        logs.add(log);
    }

    @Override
    public String deleteLog(Log log, User user) {
        switch (user.getAccessType()) {
            case ADMIN -> {
                if (user.getName().equals(log.getCreator().getName()) || log.getCreator().getAccessType().equals(AccessType.BASIC)) {
                    logs.remove(log);
                    deletedLogs.add(log);
                    return "Log deleted";
                }
                return "You don't have permission to delete this log";
            }
            case BASIC -> {
                return "You don't have permission to delete this log";
            }
            case OWNER -> {
                logs.remove(log);
                deletedLogs.add(log);
                return "Log deleted";
            }
            default -> throw new RuntimeException("Unknown access type");
        }
    }

    public List<Log> getLogs(User creator) {
        Predicate<Log> accessFilter = switch (creator.getAccessType()) {
            case OWNER -> log -> true;
            case ADMIN -> log -> log.getCreator().equals(creator) || log.getType().equals(AccessType.BASIC);
            case BASIC -> log -> log.getCreator().equals(creator);
            default -> log -> false;
        };

        return logs.stream()
                .filter(accessFilter)
                .collect(Collectors.toList());
    }

    public List<Log> getDeletedLogs(User creator) {
        Predicate<Log> accessFilter = switch (creator.getAccessType()) {
            case OWNER -> log -> true;
            case ADMIN -> log -> log.getCreator().equals(creator) || log.getType().equals(AccessType.BASIC);
            case BASIC -> log -> log.getCreator().equals(creator);
            default -> log -> false;
        };

        return deletedLogs.stream()
                .filter(accessFilter)
                .collect(Collectors.toList());
    }
}
