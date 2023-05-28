package com.example.rest.responses;

public class UserResponse {
    private String username;
    private String email;
    private String role;

    public UserResponse(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
