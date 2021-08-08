package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public void addCompany(Company company) throws CouponSystemCustomExceptions {

        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_EMAIL_EXIST);
        }

        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_NAME_EXIST);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) {

        Company companyFromDB;
        try {
            if (companyRepository.existsById(companyId)) {
                companyFromDB = companyRepository.getById(companyId);
            } else {
                throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_DO_NOT_EXIST);
            }

            if (companyId != companyFromDB.getId()) {
                throw new CouponSystemCustomExceptions(ErrMsg.CANT_UPDATE_COMPANY_ID);
            } else if (!company.getName().equals(companyFromDB.getName())) {
                throw new CouponSystemCustomExceptions(ErrMsg.CANT_UPDATE_COMPANY_NAME);
            } else {
                companyRepository.saveAndFlush(company);
            }
        } catch (CouponSystemCustomExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: delete all company coupons
    @Override
    public void deleteCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) {
        return companyRepository.getById(companyId);
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemCustomExceptions {

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemCustomExceptions(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemCustomExceptions {

        Customer customerFromDB;

        if (customerRepository.existsById(customerId)) {
            customerFromDB = customerRepository.getById(customerId);
        } else {
            throw new CouponSystemCustomExceptions(ErrMsg.CUSTOMER_DO_NOT_EXIST);
        }

        if (customerId != customerFromDB.getId()) {
            throw new CouponSystemCustomExceptions(ErrMsg.CANT_UPDATE_CUSTOMER_ID);
        } else {
            customerRepository.saveAndFlush(customer);
        }
    }

    //TODO delete all customer coupons
    @Override
    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) {
        return customerRepository.getById(customerId);
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }
}
