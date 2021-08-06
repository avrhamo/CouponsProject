package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl extends ClientService implements AdminService{

    @Override
    public void addCompany(Company company) throws CouponSystemCustomExceptions {

        if (companyRepository.existsByEmail(company.getEmail())){
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_EMAIL_EXIST);
        }

        if (companyRepository.existsByName(company.getName())){
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_NAME_EXIST);
        }

        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemCustomExceptions {

        Optional<Company> companyFromDB = companyRepository.findById(companyId);
        if(companyFromDB.get().getEmail() == company.getEmail() && companyFromDB.get().getName() == company.getName()){
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_CANT_UPDATE_COMPANY_ID);
        }
        companyRepository.saveAndFlush(company);
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
