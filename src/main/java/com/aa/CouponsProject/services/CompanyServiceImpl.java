package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService{
    @Override
    public boolean login(String email, String password) {

        return false;
    }

}
