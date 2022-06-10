package com.smtlabs.retrofittesting.model.body;

public class PostLoginCredentialModel {
    String username, password;

    public PostLoginCredentialModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
