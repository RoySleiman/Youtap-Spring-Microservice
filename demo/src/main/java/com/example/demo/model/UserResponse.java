package com.example.demo.model;

public class UserResponse {
    public Integer id;
    public String email;
    public String phone;

    public UserResponse(User user) {
        this.id = user.id;
        this.email = user.email;
        this.phone = user.phone;
    }

    public UserResponse(Integer id) {
        this.id = id;
    }
}
