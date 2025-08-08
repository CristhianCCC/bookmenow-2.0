package com.bookmenow.payment.repository;
import com.bookmenow.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
}
