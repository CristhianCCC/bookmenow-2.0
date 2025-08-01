package com.bookmenow.booking.model.enums;

public enum BookingStatus {

    RESERVED ("1"),

    AVAILABLE ("2"),

    CANCELLED ("3");

    private final String code;

    BookingStatus(String code) {
        this.code = code;
    }

    public String code(){
        return code;
    }
}
