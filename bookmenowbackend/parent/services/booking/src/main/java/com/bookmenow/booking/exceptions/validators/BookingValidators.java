package com.bookmenow.booking.exceptions.validators;
import com.bookmenow.booking.dto.BookingDTO;
import com.bookmenow.booking.repository.BookingRepository;
import com.bookmenow.catalog.exceptions.exceptions.BusinessRuleException;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class BookingValidators {

    private static void validate (BookingDTO dto, boolean isNew, BookingRepository bookingRepository) throws BusinessRuleException{

        if (dto == null){
            throw new BusinessRuleException("4001", "Booking cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if(dto.getDate().isBefore(LocalDateTime.now())){
            throw new BusinessRuleException("4002", "Date cannot be before than current date ", HttpStatus.BAD_REQUEST);
        }
        if (dto.getUserId() == null) {
            throw new BusinessRuleException("4003", "User ID is required", HttpStatus.BAD_REQUEST);
        }

        if (dto.getCatalogId() == null) {
            throw new BusinessRuleException("4004", "Catalog ID is required", HttpStatus.BAD_REQUEST);
        }
    }
}
