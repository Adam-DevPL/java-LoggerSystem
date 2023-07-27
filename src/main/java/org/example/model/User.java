package org.example.model;

import org.example.enums.AccessType;

public class User {

    private final String name;

    private final AccessType accessType;

    public User(String name, AccessType accessType) {
        this.name = name;
        this.accessType = accessType;
    }

    public String getName() {
        return name;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", accessType=" + accessType +
                '}';
    }
}
