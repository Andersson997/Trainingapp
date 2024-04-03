package com.example.myapplication;

import android.app.Application;

public class CurrentlyLoggedInUser extends Application
{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
