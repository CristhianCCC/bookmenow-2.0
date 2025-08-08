package com.bookmenow.enums;

public enum PaymentMethod {

    CARD ("1"),

    BANKTRANSFER ("2"),

    ACH ("3");

    private final String code;

    PaymentMethod (String code) { this.code = code; }

    public String code () { return code; }

}

