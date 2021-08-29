package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService{

    Company company;

    @Override
    public boolean login(String email, String password) {
        boolean correctLoginDetails = companyRepository.existsByEmailAndPassword(email, password);
        if (correctLoginDetails) {
            company = companyRepository.findByEmailAndPassword(email, password);
        }
        return correctLoginDetails;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemCustomExceptions {

        if(couponRepository.existsByCompanyIdAndTitle(company.getId(), coupon.getTitle())) {
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_ALREADY_OWN_A_COUPON_WITH_SAME_TITLE);
        }

        List<Coupon> companyCoupons = company.getCoupons();
        companyCoupons.add(coupon);
        company.setCoupons(companyCoupons);
        companyRepository.saveAndFlush(company);

    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemCustomExceptions {

        if (couponRepository.getById(coupon.getId()).getCompany().getId() == company.getId())
            throw new CouponSystemCustomExceptions(ErrMsg.UPDATING_COUPON_ID_OR_COMPANY_ID_DISALLOWED);

        if (couponRepository.getByCompanyId(coupon.getCompany().getId()).equals(coupon))
            throw new CouponSystemCustomExceptions(ErrMsg.UPDATING_COUPON_ID_OR_COMPANY_ID_DISALLOWED);

        couponRepository.saveAndFlush(coupon);

    }

    @Override
    public void deleteCoupon(Coupon coupon) {
        couponRepository.deleteById(coupon.getId());
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.getCompanyCoupons(company.getId());
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
