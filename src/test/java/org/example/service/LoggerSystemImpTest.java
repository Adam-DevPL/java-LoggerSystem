package org.example.service;

import org.example.enums.AccessType;
import org.example.model.Log;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class LoggerSystemImpTest {

    private LoggerSystem loggerSystem;
    private User owner;
    private User admin;
    private User basic;

    @BeforeEach
    public void setup() {
        loggerSystem = new LoggerSystemImp();

        owner = new User("Owner", AccessType.OWNER);
        admin = new User("Admin", AccessType.ADMIN);
        basic = new User("Basic", AccessType.BASIC);

        LocalDateTime now = LocalDateTime.now();

        // Tworzenie logów
        loggerSystem.createLog(now.minusHours(1), owner, "Log 1", AccessType.OWNER);
        loggerSystem.createLog(now.minusMinutes(30), admin, "Log 2", AccessType.ADMIN);
        loggerSystem.createLog(now.minusMinutes(20), basic, "Log 3", AccessType.BASIC);
        loggerSystem.createLog(now.minusMinutes(10), admin, "Log 4", AccessType.ADMIN);
    }

    @Test
    public void testGetLogsForOwner() {
        List<Log> logs = loggerSystem.getLogs(owner);
        assertEquals(4, logs.size());
    }

    @Test
    public void testGetLogsForAdmin() {
        List<Log> logs = loggerSystem.getLogs(admin);
        assertEquals(3, logs.size());
    }

    @Test
    public void testGetLogsForBasic() {
        List<Log> logs = loggerSystem.getLogs(basic);
        assertEquals(1, logs.size());
    }

    @Test
    public void testGetDeletedLogsForOwner() {
        List<Log> deletedLogs = loggerSystem.getDeletedLogs(owner);
        assertEquals(0, deletedLogs.size());
    }

    @Test
    public void testGetDeletedLogsForAdmin() {
        List<Log> deletedLogs = loggerSystem.getDeletedLogs(admin);
        assertEquals(0, deletedLogs.size());
    }

    @Test
    public void testGetDeletedLogsForBasic() {
        List<Log> deletedLogs = loggerSystem.getDeletedLogs(basic);
        assertEquals(0, deletedLogs.size());
    }

    @Test
    public void testDeleteLogByOwner() {
        List<Log> logs = loggerSystem.getLogs(owner);
        Log log = logs.get(0);

        String result = loggerSystem.deleteLog(log, owner);
        assertEquals("Log deleted", result);

        List<Log> deletedLogs = loggerSystem.getDeletedLogs(owner);
        assertEquals(1, deletedLogs.size());
    }

    @Test
    public void testDeleteLogByAdmin() {
        List<Log> logs = loggerSystem.getLogs(admin);
        Log log = logs.get(0);

        String result = loggerSystem.deleteLog(log, admin);
        assertEquals("Log deleted", result);

        List<Log> deletedLogs = loggerSystem.getDeletedLogs(admin);
        assertEquals(1, deletedLogs.size());
    }

    @Test
    public void testDeleteLogByBasic() {
        List<Log> logs = loggerSystem.getLogs(basic);
        Log log = logs.get(0);

        assertThrowsExactly(IllegalArgumentException.class, () -> loggerSystem.deleteLog(log, basic), "You don't have permission to delete this log");


        List<Log> deletedLogs = loggerSystem.getDeletedLogs(basic);
        assertEquals(0, deletedLogs.size());
    }
}