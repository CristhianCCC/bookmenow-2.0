package com.bookmenow.payment.exceptions.exceptions;
import org.springframework.http.HttpStatus;

public class BusinessRuleException extends RuntimeException {

    //adding attributes considered as necessary
    private Long id;
    private String code;
    private HttpStatus httpStatus;

    public BusinessRuleException(String message, String code, HttpStatus httpStatus, Long id) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.id = id;
    }

    public BusinessRuleException(Throwable cause, String message) {
        super(message, cause);
    }

    public BusinessRuleException(String code, String message, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
