package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public void addCompany(Company company) throws CouponSystemCustomExceptions {

        if (companyRepository.existsByName(company.getName()))
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_NAME_EXIST);

        if (companyRepository.existsByEmail(company.getEmail()))
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_EMAIL_EXIST);

        companyRepository.saveAndFlush(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemCustomExceptions {

        Company companyFromDB;

        if (companyRepository.existsById(companyId))
            companyFromDB = companyRepository.getById(companyId);
        else throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_DO_NOT_EXIST);

        if (company.getId() != companyFromDB.getId())
            throw new CouponSystemCustomExceptions(ErrMsg.CANT_UPDATE_COMPANY_ID);

        else if (!company.getName().equals(companyFromDB.getName()))
            throw new CouponSystemCustomExceptions(ErrMsg.CANT_UPDATE_COMPANY_NAME);
        else companyRepository.saveAndFlush(company);

    }

    @Override
    public void deleteCompany(int companyId) {

        // Get all company coupons
        List<Coupon> companyCoupons = couponRepository.getAllByCompanyId(companyId);

        // Delete all coupons from customers
        companyCoupons.forEach(coupon -> couponRepository.deleteCouponFromCustomers(coupon.getId()));

        // Delete Coupons from coupon table
        couponRepository.deleteCouponByCompanyId1(companyId);

        // Delete Company
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

        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new CouponSystemCustomExceptions(ErrMsg.CUSTOMER_EMAIL_EXIST);

        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemCustomExceptions {

        Customer customerFromDB;

        if (customerRepository.existsById(customerId))
            customerFromDB = customerRepository.getById(customerId);
        else
            throw new CouponSystemCustomExceptions(ErrMsg.CUSTOMER_DO_NOT_EXIST);

        if (customer.getId() != customerFromDB.getId())
            throw new CouponSystemCustomExceptions(ErrMsg.CANT_UPDATE_CUSTOMER_ID);
         else
             customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {

        // Delete customer coupons
        couponRepository.deleteCustomerCoupons(customerId);

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
    public boolean login(String email, String password) throws CouponSystemCustomExceptions {
        if(email.equals("admin@admin.com") && password.equals("admin"))
            return true;
        throw new CouponSystemCustomExceptions(ErrMsg.WRONG_LOGIN_DETAILS);
    }
}
