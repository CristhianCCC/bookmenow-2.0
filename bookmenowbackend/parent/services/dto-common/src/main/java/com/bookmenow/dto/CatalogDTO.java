package com.bookmenow.dto;
import com.bookmenow.enums.UserRoles;

import java.time.LocalDateTime;

public class CatalogDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double durationMinutes;

    private String category;

    private String availableDays;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * calling user-provider attributes to be used on the service
     */

    private Long providerId;

    private String providerName;

    private UserRoles role;

    private String providerEmail;

    private String providerAdress;

    public CatalogDTO () {}

    public CatalogDTO(String availableDays, String category, LocalDateTime createdAt, String description,
                      Double durationMinutes, Long id, String name, Double price, String providerAdress, String providerEmail,
                      Long providerId, String providerName, UserRoles role, LocalDateTime updatedAt) {
        this.availableDays = availableDays;
        this.category = category;
        this.createdAt = createdAt;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.id = id;
        this.name = name;
        this.price = price;
        this.providerAdress = providerAdress;
        this.providerEmail = providerEmail;
        this.providerId = providerId;
        this.providerName = providerName;
        this.role = role;
        this.updatedAt = updatedAt;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Double durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProviderAdress() {
        return providerAdress;
    }

    public void setProviderAdress(String providerAdress) {
        this.providerAdress = providerAdress;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
