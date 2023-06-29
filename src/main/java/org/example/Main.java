package org.example;

import org.example.enums.AccessType;
import org.example.model.User;
import org.example.service.LoggerSystem;
import org.example.service.LoggerSystemImp;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LoggerSystem loggerSystem = new LoggerSystemImp();

        User user1 = new User("user1", AccessType.BASIC);
        User user2 = new User("user2", AccessType.ADMIN);
        User user3 = new User("user3", AccessType.OWNER);

        loggerSystem.createLog(LocalDateTime.now(), user1, "log1", user1.getAccessType());
        loggerSystem.createLog(LocalDateTime.now(), user2, "log2", user2.getAccessType());
        loggerSystem.createLog(LocalDateTime.now(), user3, "log3", user3.getAccessType());

        System.out.println("Logs: " + user1);
        System.out.println(loggerSystem.getLogs(user1));

        System.out.println("Logs: " + user2);
        loggerSystem.getLogs(user2).forEach(System.out::println);

        System.out.println("Logs: " + user3);
        loggerSystem.getLogs(user3).forEach(System.out::println);

        System.out.println("Deleted logs: " + user1);
        System.out.println(loggerSystem.deleteLog(loggerSystem.getLogs(user1).get(0), user2));

    }
}