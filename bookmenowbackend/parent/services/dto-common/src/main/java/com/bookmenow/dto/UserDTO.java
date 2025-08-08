package com.bookmenow.dto;
import com.bookmenow.enums.UserRoles;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private UserRoles userRoles;

    private String phone;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserDTO() {}

    public UserDTO(String address, LocalDateTime createdAt, String email, Long id, String lastName, String name, String password, String phone, LocalDateTime updatedAt, UserRoles userRoles) {
        this.address = address;
        this.createdAt = createdAt;
        this.email = email;
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.updatedAt = updatedAt;
        this.userRoles = userRoles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}

