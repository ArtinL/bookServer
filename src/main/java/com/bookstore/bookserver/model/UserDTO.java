package com.bookstore.bookserver.model;

import com.bookstore.bookserver.entities.ApplicationUser;

@SuppressWarnings("unused")
public class UserDTO {

    private Integer userId;
    private String username;


    public UserDTO() {
        super();
    }

    public UserDTO(ApplicationUser user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }

    public UserDTO(int userId, String username) {
        this.userId = userId;
        this.username = username;
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
