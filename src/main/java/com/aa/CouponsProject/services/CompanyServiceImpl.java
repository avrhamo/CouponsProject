package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService{

    Company company;

    @Override
    public boolean login(String email, String password) throws CouponSystemCustomExceptions {

        if (companyRepository.existsByEmailAndPassword(email, password))
            company = companyRepository.findByEmailAndPassword(email, password);
        else
            throw new CouponSystemCustomExceptions(ErrMsg.WRONG_LOGIN_DETAILS);

        return true;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemCustomExceptions {

        if(couponRepository.existsByCompanyIdAndTitle(company.getId(), coupon.getTitle()))
            throw new CouponSystemCustomExceptions(ErrMsg.COMPANY_ALREADY_OWN_A_COUPON_WITH_SAME_TITLE);

        coupon.setCompany(company);
        couponRepository.saveAndFlush(coupon);

        company.getCoupons().add(coupon);
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemCustomExceptions {

        if (couponRepository.getById(coupon.getId()).getCompany().getId() == company.getId())
            throw new CouponSystemCustomExceptions(ErrMsg.UPDATING_COUPON_IS_OR_COMPANY_IS_DISALLOWED);

        if (couponRepository.getByCompanyId(coupon.getCompany().getId()).equals(coupon))
            throw new CouponSystemCustomExceptions(ErrMsg.UPDATING_COUPON_IS_OR_COMPANY_IS_DISALLOWED);

        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(Coupon coupon) {
        couponRepository.deleteCouponFromCustomers(coupon.getId());
        couponRepository.deleteById(coupon.getId());
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.getAllByCompanyId(company.getId());
    }

    @Override
    public List<Coupon> getCompanyCoupons(Categories category) {
        return couponRepository.getAllByCompanyIdAndCategoryId(company.getId(), category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {

        return company
                .getCoupons()
                .stream()
                .filter(coupon -> coupon.getPrice() < maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public Company getCompanyDetails() {
        return companyRepository.getById(company.getId());
    }
}
