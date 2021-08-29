package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;

import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon) throws CouponSystemCustomExceptions;

    void updateCoupon(Coupon coupon) throws CouponSystemCustomExceptions;

    void deleteCoupon(Coupon coupon);

    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(Categories category);

    List<Coupon> getCompanyCoupons(double maxPrice);

    Company getCompanyDetails();

}
