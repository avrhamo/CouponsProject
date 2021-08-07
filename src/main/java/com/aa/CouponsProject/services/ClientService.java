package com.aa.CouponsProject.services;

import com.aa.CouponsProject.CouponsProjectApplication;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.repos.CompanyRepository;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.repos.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CouponRepository couponRepository;

    public ClientService() {
    }

    public abstract boolean login(String email, String password);
}
