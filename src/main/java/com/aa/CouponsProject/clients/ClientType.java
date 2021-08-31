package com.aa.CouponsProject.clients;

public enum ClientType {

    ADMIN,
    COMPANY,
    CUSTOMER;
    public final int value = 1 + ordinal();
}