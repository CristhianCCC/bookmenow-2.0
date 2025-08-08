package com.bookmenow.enums;

public enum PaymentStatus {

    PENDING ("1"),

    CANCELLED ("2"),

    PAID ("3");

    private final String code;

    PaymentStatus(String code) {this.code = code;}

    public String code() { return code; }

}
