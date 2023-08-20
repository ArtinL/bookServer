package com.bookstore.bookserver.model.authdtos;

@SuppressWarnings("unused")
public class CredentialsDTO {
    private String username;
    private String password;
    private String email;

    public CredentialsDTO() {
        super();
    }

    public CredentialsDTO(String email, String username, String password) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Registration Info: username=" + username + ", password=" + password;
    }
}
