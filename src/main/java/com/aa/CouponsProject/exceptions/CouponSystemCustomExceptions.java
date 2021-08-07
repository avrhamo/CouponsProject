package com.aa.CouponsProject.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class CouponSystemCustomExceptions extends Exception{

    public CouponSystemCustomExceptions(ErrMsg errMsg) {
        super(errMsg.getDescription());
    }
}
