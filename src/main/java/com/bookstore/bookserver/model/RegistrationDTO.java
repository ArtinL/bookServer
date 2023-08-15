package com.bookstore.bookserver.model;

public class RegistrationDTO {
    private String username;
    private String password;

    public RegistrationDTO() {
        super();
    }

    public RegistrationDTO(String username, String password) {
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
