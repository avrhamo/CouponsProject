package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import com.aa.CouponsProject.repos.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService{


    @Override
    public void addCompany(Company company) throws CouponSystemCustomExceptions {

        if (companyRepository.existsByName(company.getName())){
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_NAME_EXIST);
        }

        if (companyRepository.existsByName(company.getName())){
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_NAME_EXIST);
        }

        this.addCompany(company);

    }

    @Override
    public void updateCompany(int companyId, Company company) {

    }

    @Override
    public void deleteCompany(int companyId) {

    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getSingleCompany(int companyId) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(int customerId, Customer customer) {

    }

    @Override
    public void deleteCustomer(int customerId) {

    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getSingleCustomer(int customerId) {
        return null;
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }
}
