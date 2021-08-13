package com.aa.CouponsProject.services;


import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;

import java.util.List;

public interface CustomerService {

    void AddCouponPurchase(Coupon coupon) throws CouponSystemCustomExceptions;

    List<Coupon> getAllCoupons(int customerId) throws CouponSystemCustomExceptions;

    List<Coupon> getCouponsByCategory(int CustomerId, Categories category) throws CouponSystemCustomExceptions;

    List<Coupon> getAllCouponsUpToMaxPrice(int CustomerId, Categories category) throws CouponSystemCustomExceptions;

    Customer getCustomerDetails(int CustomerId);


}
