package com.bookmenow.payment.service.serviceImpl;
import com.bookmenow.dto.BookingDTO;
import com.bookmenow.dto.CatalogDTO;
import com.bookmenow.dto.PaymentDTO;
import com.bookmenow.dto.UserDTO;
import com.bookmenow.enums.PaymentStatus;
import com.bookmenow.payment.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.payment.exceptions.validators.PaymentValidators;
import com.bookmenow.payment.feign.clients.BookingClient;
import com.bookmenow.payment.feign.clients.CatalogClient;
import com.bookmenow.payment.feign.clients.UserClient;
import com.bookmenow.payment.model.Payment;
import com.bookmenow.payment.repository.PaymentRepository;
import com.bookmenow.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private BookingClient bookingClient;

    //dto to entity ----------------------------------------------------------------------------------------------------
    private Payment toEntity (PaymentDTO dto){
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setUserId(dto.getUserId());
        payment.setCatalogId(dto.getCatalogId());
        payment.setBookingId(dto.getBookingId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(dto.getStatus());
        if(dto.getStatus().equals(PaymentStatus.PAID)){
            payment.setPaidAt(LocalDateTime.now());
        }
        UserDTO userDTO = userClient.getUserById(payment.getUserId());
        if (userDTO == null || userDTO.getId() == null){
            throw new BusinessRuleException("User with the ID" + dto.getUserId() + " Was not found", "6003", HttpStatus.BAD_REQUEST);
        }

        //getting user email
        payment.setUserEmail(userDTO.getEmail());

        CatalogDTO catalogDTO = catalogClient.getCatalogById(payment.getCatalogId());
        if(catalogDTO == null || catalogDTO.getId() == null){
            throw new BusinessRuleException("Catalog with the ID" + dto.getCatalogId() + " was not found", "6003", HttpStatus.BAD_REQUEST);
        }

        //getting catalog name and price
        payment.setCatalogName(catalogDTO.getName());
        payment.setCatalogPrice(catalogDTO.getPrice());

        BookingDTO bookingDTO = bookingClient.findBookingById(payment.getBookingId());
        if(bookingDTO == null || bookingDTO.getId() == null) {
            throw new BusinessRuleException("Booking with the ID" + dto.getBookingId() + " was not found", "6003", HttpStatus.BAD_REQUEST);
        }

        //getting booking id
        payment.setBookingId(bookingDTO.getId());

        return payment;
    }
    //------------------------------------------------------------------------------------------------------------------

    //entity to dto ----------------------------------------------------------------------------------------------------
    private PaymentDTO toDTO(Payment payment){
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setPaidAt(payment.getPaidAt());
        UserDTO userDTO = userClient.getUserById(payment.getUserId());
        if (userDTO.getId() == null){
            throw new BusinessRuleException("user with the ID " + payment.getUserId() + " was not found", "6003", HttpStatus.BAD_REQUEST);
        }
        //getting user email
        dto.setUserId(userDTO.getId());
        dto.setUserEmail(userDTO.getEmail());

        CatalogDTO catalogDTO = catalogClient.getCatalogById(payment.getCatalogId());
        if(catalogDTO.getId() == null){
            throw new BusinessRuleException("Catalog with the ID " + payment.getCatalogId() + " was not found", "6003", HttpStatus.BAD_REQUEST);
        }
        //getting catalog name and price
        dto.setCatalogId(catalogDTO.getId());
        dto.setCatalogName(catalogDTO.getName());
        dto.setCatalogPrice(catalogDTO.getPrice());

        BookingDTO bookingDTO = bookingClient.findBookingById(payment.getBookingId());
        if(bookingDTO.getId() == null){
            throw new BusinessRuleException("Booking with the ID " + payment.getBookingId() + " Was not found", "6003", HttpStatus.BAD_REQUEST);
        }
        //getting booking id
        dto.setBookingId(bookingDTO.getId());

        return dto;
    }


    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /*@Override
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        List<Payment> paymentUser = paymentRepository.findAllPaymentsByUserId(userId);
        return paymentUser.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }*/

    //if payment is PAID set localdatetime
    // if amount === to catalog.getamount otherwise payment will fail
    //if paymentmethod is card ask for card number, if payment method is bank transfer ask for bank account number
    @Override
    public PaymentDTO postPayment(PaymentDTO paymentDTO) {
        /*PaymentValidators.validate(true, paymentDTO, paymentRepository);*/
        Payment newPayment = toEntity(paymentDTO);
        if (!newPayment.getAmount().equals(newPayment.getCatalogPrice())){
            throw new BusinessRuleException("Payment amount is not complete, pay the rest to get the service", "6006", HttpStatus.BAD_REQUEST);
        }
        newPayment.setStatus(PaymentStatus.PAID);
        newPayment.setPaidAt(LocalDateTime.now());
        paymentRepository.save(newPayment);
        return toDTO(newPayment);
    }

    @Override
    public PaymentDTO putPayment(Long id, PaymentDTO paymentDTO) {
        PaymentValidators.validate(false, paymentDTO, paymentRepository);
       return paymentRepository.findById(id).map(payment -> {
            payment.setAmount(paymentDTO.getAmount());
           if(!payment.getAmount().equals(payment.getCatalogPrice())){
               throw new BusinessRuleException("Payment amount is not the same as catalog amount, please pay the rest", "6009", HttpStatus.BAD_REQUEST);
           }
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            Payment paymentEdited = paymentRepository.save(payment);
            return toDTO(paymentEdited);
        }).orElseThrow(() -> new BusinessRuleException("Something went wrong, please check all fields", "6007", HttpStatus.BAD_REQUEST));
    }

    @Override
    public PaymentDTO cancelPayment(Long id) {
        return paymentRepository.findById(id).map(payment -> {
            if (payment.getStatus().equals(PaymentStatus.PAID)){
                throw new BusinessRuleException("Payment cannot be cancelled, please contact your bank", "6008", HttpStatus.BAD_REQUEST);
            }
            if(payment.getStatus().equals(PaymentStatus.CANCELLED)){
                throw new BusinessRuleException("The payment is already cancelled", "6010", HttpStatus.BAD_REQUEST);
            }
            payment.setStatus(PaymentStatus.CANCELLED);
            Payment paymentSaved = paymentRepository.save(payment);
            return toDTO(paymentSaved);
        }).orElseThrow(() -> new BusinessRuleException("Payment with id " + id + " Was not found", "6003", HttpStatus.BAD_REQUEST));
    }
}
