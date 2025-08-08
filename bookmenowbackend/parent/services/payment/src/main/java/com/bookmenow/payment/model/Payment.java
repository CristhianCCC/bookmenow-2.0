package com.bookmenow.payment.model;
import com.bookmenow.enums.PaymentMethod;
import com.bookmenow.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @CreationTimestamp
    private LocalDateTime paidAt;

    //accessing to booking attributes -----------------------------------------------------------------------------------

    private Long bookingId;


    //------------------------------------------------------------------------------------------------------------------

    //accessing to user attributes -------------------------------------------------------------------------------------

    private Long userId;

    private String userEmail;

    //------------------------------------------------------------------------------------------------------------------

    //accessing to catalog attributes ----------------------------------------------------------------------------------

    private Long catalogId;

    private String catalogName;

    private Double catalogPrice;

    //------------------------------------------------------------------------------------------------------------------

    public Payment () {}


    public Payment(Double amount, Long bookingId, Long catalogId, String catalogName, Long id, LocalDateTime paidAt,
                   PaymentMethod paymentMethod, String userEmail, PaymentStatus status, Long userId, Double catalogPrice) {
        this.amount = amount;
        this.bookingId = bookingId;
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.id = id;
        this.paidAt = paidAt;
        this.paymentMethod = paymentMethod;
        this.userEmail = userEmail;
        this.status = status;
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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

    public Double getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(Double catalogPrice) {
        this.catalogPrice = catalogPrice;
    }
}
