package com.bookmenow.user.exceptions.validators;
import com.bookmenow.user.dto.UserDTO;
import com.bookmenow.user.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.user.repository.UserRepository;
import org.springframework.http.HttpStatus;

public class UserValidators {

    public static void validate (UserDTO dto, boolean isNew, UserRepository userRepository) throws BusinessRuleException {

        if (dto == null) {
            throw new BusinessRuleException("2001", "User can't be empty", HttpStatus.BAD_REQUEST);
        }

        if (dto.getName() == null || dto.getName().isEmpty() || dto.getLastName() == null || dto.getLastName().isEmpty()
                || dto.getEmail() == null || dto.getEmail().isEmpty() ||
                dto.getPassword() == null || dto.getPassword().isEmpty() || dto.getAddress() == null ||
                dto.getAddress().isEmpty()|| dto.getPhone() == null || dto.getPhone().isEmpty()) {
            throw new BusinessRuleException("2002", "All fields are required", HttpStatus.BAD_REQUEST);
        }

        if (isNew) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new BusinessRuleException("2003", "The email " + dto.getEmail() + " is already in use", HttpStatus.CONFLICT);
            }
        } else {
            // Validate that ID won't be null while editing
            if (dto.getId() == null) {
                throw new BusinessRuleException("2000", "User ID is required for update", HttpStatus.BAD_REQUEST);
            }

            userRepository.findByEmail(dto.getEmail()).ifPresent(existingUser -> {
                if (!existingUser.getId().equals(dto.getId())) {
                    throw new BusinessRuleException("2003", "The email " + dto.getEmail() + " is already in use by another user", HttpStatus.CONFLICT);
                }
            });
        }

        if(dto.getUserRoles().name() == null || dto.getUserRoles().name().isEmpty()){
            throw new BusinessRuleException("2004", "ROLE TYPE IS NOT ACCEPTED, INPUT ANY OF THE FOLLOWING ROLES [USER / PROVIDER]", HttpStatus.BAD_REQUEST);
        }
        //
        /*
        try {
            dto.getRole().name(); // asegúrate que es un enum válido
        } catch (Exception e) {
            throw new BusinessRuleException("2004", "Invalid role", HttpStatus.BAD_REQUEST);
        }
        */
    }
    /*
    public static void validateCredentials(AuthRequest dto) throws BusinessRuleException {
        if (dto == null) {
            throw new BusinessRuleException("2005", "Credentials cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
    }*/
}
