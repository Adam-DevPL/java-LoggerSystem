package org.example.model;

import org.example.enums.AccessType;

public class User {

    final String name;

    final AccessType accessType;

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
