package com.bookmenow.user.model;
import com.bookmenow.enums.UserRoles;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    private String phone;

    private String address;

    @UpdateTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;


    public User () {}

    public User(LocalDateTime createdAt, String email, Long id, String lastName, String name, String password, LocalDateTime updatedAt, UserRoles userRoles, String phone, String address) {
        this.createdAt = createdAt;
        this.email = email;
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.updatedAt = updatedAt;
        this.userRoles = userRoles;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
