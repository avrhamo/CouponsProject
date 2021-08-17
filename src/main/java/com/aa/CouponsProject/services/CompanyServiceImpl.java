package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService{
    @Override
    public boolean login(String email, String password) {

        return false;
    }

    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(Coupon coupon) {

    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return null;
    }

    @Override
    public List<Coupon> getCompanyCoupons(Categories category) {
        return null;
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return null;
    }

    @Override
    public Company getCompanyDetails() {
        return null;
    }
}
