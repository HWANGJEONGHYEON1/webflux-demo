package com.example.springwebfluxdemo;

import java.util.Objects;

public class User {

    private String username;

    public User(){}

    public User(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
