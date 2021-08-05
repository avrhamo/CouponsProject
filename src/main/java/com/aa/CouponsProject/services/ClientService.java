package com.aa.CouponsProject.services;

import com.aa.CouponsProject.repos.CompanyRepository;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.repos.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CouponRepository couponRepository;

    public abstract boolean login(String email, String password);
}
