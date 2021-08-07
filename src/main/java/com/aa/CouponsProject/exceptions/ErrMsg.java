package com.aa.CouponsProject.exceptions;

public enum ErrMsg {

    COMPANY_EMAIL_EXIST("Company email is already exist"),
    COMPANY_NAME_EXIST("Company name is already exist"),
    COMPANY_DO_NOT_EXIST("Company is not exist in DB"),
    CANT_UPDATE_COMPANY_ID("Can't update company id"),
    CANT_UPDATE_COMPANY_NAME("Can't update company name"),

    CUSTOMER_EMAIL_EXIST("Customer email is already exist"),
    CUSTOMER_DO_NOT_EXIST("Customer is not exist in DB"),
    CANT_UPDATE_CUSTOMER_ID("Can't update customer id")

    ;

    private String description;

    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
