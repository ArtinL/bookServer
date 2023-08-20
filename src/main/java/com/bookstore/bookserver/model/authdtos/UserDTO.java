package com.bookstore.bookserver.model.authdtos;

import com.bookstore.bookserver.entities.ApplicationUser;

@SuppressWarnings("unused")
public class UserDTO {

    private Integer userId;
    private String username;
    private String email;


    public UserDTO() {
        super();
    }

    public UserDTO(ApplicationUser user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public UserDTO(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
