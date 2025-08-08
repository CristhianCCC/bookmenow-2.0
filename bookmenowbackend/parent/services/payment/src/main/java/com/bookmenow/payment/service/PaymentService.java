package com.bookmenow.payment.service;

import com.bookmenow.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    public List<PaymentDTO> getAllPayments ();

    //public List<PaymentDTO> getPaymentsByUserId(Long userId);

    public PaymentDTO postPayment (PaymentDTO paymentDTO);

    public PaymentDTO putPayment (Long id, PaymentDTO paymentDTO);

    public PaymentDTO cancelPayment (Long id);

}
