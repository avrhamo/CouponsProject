package com.aa.CouponsProject.services;


import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;

import java.util.List;

public interface AdminService {

    //Company CRUD
    void addCompany(Company company) throws CouponSystemCustomExceptions;
    void updateCompany(int companyId, Company company) throws CouponSystemCustomExceptions;
    void deleteCompany(int companyId);
    List<Company> getAllCompanies();
    Company getSingleCompany(int companyId);

    //Customer CRUD
    void addCustomer(Customer customer);
    void updateCustomer(int customerId, Customer customer);
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
    Customer getSingleCustomer(int customerId);

}
