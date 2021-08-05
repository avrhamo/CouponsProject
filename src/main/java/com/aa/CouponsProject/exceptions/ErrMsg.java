package com.aa.CouponsProject.exceptions;

public enum ErrMsg {

    COMPANY_EMAIL_EXIST("Company email is already exist"),
    COMPANY_NAME_EXIST("Company name is already exist");

    private String description;
    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
