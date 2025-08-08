package com.bookmenow.payment.controller;
import com.bookmenow.dto.PaymentDTO;
import com.bookmenow.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments () {
        List<PaymentDTO> paymentDTOS = paymentService.getAllPayments();
        return ResponseEntity.ok(paymentDTOS);
    }

    /*@GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getAllPaymentsByUserId (@PathVariable("id") Long userId){
        List<PaymentDTO> paymentDTOS = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(paymentDTOS);
    }*/

    @PostMapping
    public ResponseEntity<PaymentDTO> postPayment (@RequestBody PaymentDTO paymentDTO){
        PaymentDTO paymentDTO1 = paymentService.postPayment(paymentDTO);
        return ResponseEntity.ok(paymentDTO1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> putPayment (@PathVariable("id") Long id, @RequestBody PaymentDTO paymentDTO){
        PaymentDTO paymentDTO1 = paymentService.putPayment(id, paymentDTO);
        return ResponseEntity.ok(paymentDTO1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PaymentDTO> cancelPayment (@PathVariable Long id){
        PaymentDTO paymentDTO = paymentService.cancelPayment(id);
        return ResponseEntity.ok(paymentDTO);
    }
}
