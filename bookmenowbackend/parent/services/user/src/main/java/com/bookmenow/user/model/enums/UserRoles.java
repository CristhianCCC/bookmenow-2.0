package com.bookmenow.user.model.enums;

public enum UserRoles {

    CLIENT ("1"),

    PROVIDER ("2");

    private final String code;

    UserRoles(String code) {
        this.code = code;
    }

    public String code(){
        return code;
    }

}
