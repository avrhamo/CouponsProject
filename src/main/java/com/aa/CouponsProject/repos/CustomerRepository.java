package com.aa.CouponsProject.repos;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);

    //List<Coupon> findByCategoryAndClientId(int customerId, Categories category);
}
