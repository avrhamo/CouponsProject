package com.aa.CouponsProject.exceptions;

public enum ErrMsg {

    COMPANY_EMAIL_EXIST("Company email is already exist"),
    COMPANY_NAME_EXIST("Company name is already exist"),
    COMPANY_CANT_UPDATE_COMPANY_ID("Can't update company id"),
    COMPANY_CANT_UPDATE_COMPANY_NAME("Can't update company name");

    private String description;
    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
