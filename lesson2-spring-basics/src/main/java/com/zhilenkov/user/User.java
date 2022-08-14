package com.zhilenkov.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
    }
}

