package com.bookmenow.payment.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;

    private String paymentMethod;

    private String status;

    @CreationTimestamp
    private LocalDate paidAt;

    //accessing to booking attributes -----------------------------------------------------------------------------------

    private Long bookingId;

    //------------------------------------------------------------------------------------------------------------------

    //accessing to user attributes -------------------------------------------------------------------------------------

    private Long userId;

    //------------------------------------------------------------------------------------------------------------------


}
