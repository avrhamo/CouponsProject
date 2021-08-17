package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;

import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(Coupon coupon);

    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(Categories category);

    List<Coupon> getCompanyCoupons(double maxPrice);

    Company getCompanyDetails();

}
