package com.hell.buildingsupplies;

import org.jetbrains.annotations.NotNull;

public class User {

    private final int id;
    private final String username;
    private final String email;


    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @NotNull
    @Override
    public String toString() {
        return "ID: "+id+"  Username: "+username+"  E-mail: "+email;
    }
}
