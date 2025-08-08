package com.bookmenow.payment.exceptions.validators;
import com.bookmenow.dto.PaymentDTO;
import com.bookmenow.enums.PaymentMethod;
import com.bookmenow.enums.PaymentStatus;
import com.bookmenow.payment.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.payment.repository.PaymentRepository;
import org.springframework.http.HttpStatus;

public class PaymentValidators {

    public static void validate (boolean isNew, PaymentDTO dto, PaymentRepository paymentRepository){

        if (dto == null){
            throw new BusinessRuleException("Payment cannot be empty", "6001", HttpStatus.BAD_REQUEST);
        }

        if (!dto.getPaymentMethod().equals(PaymentMethod.CARD) || !dto.getPaymentMethod().equals(PaymentMethod.BANKTRANSFER) || !dto.getPaymentMethod().equals(PaymentMethod.ACH)){
            throw new BusinessRuleException("Payment method no available, please select [CARD, ACH OR BANK TRANSFER]", "6002", HttpStatus.BAD_REQUEST);
        }
        if (dto.getStatus().equals(PaymentStatus.CANCELLED)){
            throw new BusinessRuleException("Payment is already cancelled, you must set up a new transfer", "6009", HttpStatus.BAD_REQUEST);
        }
    }
}
