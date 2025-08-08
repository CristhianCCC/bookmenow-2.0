package com.bookmenow.catalog.exceptions.validators;
import com.bookmenow.catalog.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.catalog.repository.CatalogRepository;
import com.bookmenow.dto.CatalogDTO;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class CatalogValidators {
    public static void validate (CatalogDTO dto, boolean isNew, CatalogRepository catalogRepository) throws BusinessRuleException {
        if(dto == null){
            throw new BusinessRuleException("3003", "catalog cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if(dto.getName() == null || dto.getName().isEmpty() || dto.getDescription() == null || dto.getDescription().isEmpty()
        || dto.getCategory() == null || dto.getCategory().isEmpty()){
            throw new BusinessRuleException("3004", "All fields are required", HttpStatus.BAD_REQUEST);
        }
        if(dto.getPrice() <= 0){
            throw new BusinessRuleException("3005", "Price cannot be 0 or lower", HttpStatus.BAD_REQUEST);
        }
        if(dto.getDurationMinutes() <= 10) {
            throw new BusinessRuleException("3006", "Duration cannot be 10 minutes or under", HttpStatus.BAD_REQUEST);
        }
        if(/*dto.getAvailableDays().before(Date.from(Instant.now()))*/ dto.getAvailableDays().isEmpty() || dto.getAvailableDays() == null){
            throw new BusinessRuleException("3007", "Something is wrong with the date 'it must be after current date'", HttpStatus.BAD_REQUEST);
        }
        if(!isNew){
            if(!catalogRepository.existsById(dto.getId())){
                throw new BusinessRuleException("3008", "Catalog with id " + dto.getId() + "was not found", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
