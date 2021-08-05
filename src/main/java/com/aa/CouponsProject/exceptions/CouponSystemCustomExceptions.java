package com.aa.CouponsProject.exceptions;

public class CouponSystemCustomExceptions extends Exception{

    public CouponSystemCustomExceptions(ErrMsg errMsg) {
        super(errMsg.getDescription());
    }
}
