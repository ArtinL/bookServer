package com.bookstore.bookserver.model;

@SuppressWarnings("unused")
public class CredentialsDTO {
    private String username;
    private String password;

    public CredentialsDTO() {
        super();
    }

    public CredentialsDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Registration Info: username=" + username + ", password=" + password;
    }
}
