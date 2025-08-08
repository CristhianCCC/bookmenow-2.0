package com.bookmenow.booking.exceptions.exceptions;
import com.bookmenow.booking.exceptions.common.StandardizedApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Handler for business rule violations.
     */
    @ExceptionHandler(com.bookmenow.booking.exceptions.exceptions.BusinessRuleException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessRuleException(BusinessRuleException ex){
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(
                ex.getCode(),
                ex.getMessage(),
                "/errors/business/" + (ex.getId() != null ? ex.getId() : "unknown"),
                "/Business Rule Violation",
                "/errors/business"
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }
    /**
     * Fallback handler for unhandled exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.bookmenow.booking.exceptions.common.StandardizedApiExceptionResponse> handleGenericException(Exception ex) {
        com.bookmenow.booking.exceptions.common.StandardizedApiExceptionResponse response = new com.bookmenow.booking.exceptions.common.StandardizedApiExceptionResponse(
                "5000",
                "Unexpected error: " + ex.getMessage(),
                "/errors/server/unexpected",
                "Internal Server Error",
                "/errors/server"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
