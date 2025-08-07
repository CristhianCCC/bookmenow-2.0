package com.bookmenow.booking.model;
import com.bookmenow.booking.model.enums.BookingStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * user attributes -------------------------------------------------------------------------------------------------
     */
    private Long userId;

    private String userName;

    private String userEmail;

    private String phone;

    private String address;

    //------------------------------------------------------------------------------------------------------------------

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * catalog attributes-----------------------------------------------------------------------------------------------
     */

    private Long catalogId;

    private String catalogName;

    private String catalogDescription;

    private Double durationMinutes;

    private Double price;

    //------------------------------------------------------------------------------------------------------------------

    public Booking() {}

    public Booking(String address, BookingStatus bookingStatus, String catalogDescription, Long catalogId, String catalogName,
                   LocalDateTime createdAt, LocalDateTime date, Double durationMinutes, Long id, String phone,
                   Double price, LocalDateTime updatedAt, String userEmail, Long userId, String userName) {
        this.address = address;
        this.bookingStatus = bookingStatus;
        this.catalogDescription = catalogDescription;
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.createdAt = createdAt;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.id = id;
        this.phone = phone;
        this.price = price;
        this.updatedAt = updatedAt;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}