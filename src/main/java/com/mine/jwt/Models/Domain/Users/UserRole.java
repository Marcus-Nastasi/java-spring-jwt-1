package com.mine.jwt.Models.Domain.Users;

public enum UserRole {

    ADMIN("admin"), USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}


