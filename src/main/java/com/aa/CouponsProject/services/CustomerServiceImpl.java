package com.aa.CouponsProject.services;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.repos.CustomerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private Customer customer;
    private final CouponRepository couponRepository;

    @Override
    public boolean login(String email, String password) {
        boolean correctLoginDetails = customerRepository.existsByEmailAndPassword(email, password);
        if (correctLoginDetails) {
            customer = customerRepository.findByEmailAndPassword(email, password);
        }
        return correctLoginDetails;
    }

    @Override
    public void AddCouponPurchase(Coupon coupon) throws CouponSystemCustomExceptions {

        Coupon couponToBuy = couponRepository.getById(coupon.getId());

        if (couponToBuy.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemCustomExceptions(ErrMsg.COUPON_EXPIRED_ERROR);
        }
        if (couponToBuy.getAmount() == 0) {
            throw new CouponSystemCustomExceptions(ErrMsg.COUPON_SOLD_OUT);
        }

        List<Coupon> customerCoupons = customer.getCoupons();

        customerCoupons.add(coupon);
        customer.setCoupons(null);
        customerRepository.saveAndFlush(customer);

        customer.setCoupons(customerCoupons);
        customerRepository.saveAndFlush(customer);

    }

    @Override
    public List<Coupon> getAllCoupons(int customerId) throws CouponSystemCustomExceptions {
        return customerRepository.getById(customerId).getCoupons();
    }

    @Override
    public List<Coupon> getCouponsByCategory(int customerId, Categories category) throws CouponSystemCustomExceptions {

//        List<Coupon> customerCoupons = this.getAllCoupons(customerId);
//        if (customerCoupons == null) {
//            return null;
//        }
//        return customerCoupons.stream().filter(c -> c.getCategoryId().equals(category.ordinal())).collect(Collectors.toList());
//
//        customer.setCoupons(this.getAllCoupons(customerId));
        if (customer.getCoupons() == null) {
            return null;
        }
        return customer.getCoupons().stream().filter(c -> c.getCategoryId().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Coupon> getAllCouponsUpToMaxPrice(int CustomerId, Categories category) throws CouponSystemCustomExceptions {
        return null;
    }

    @Override
    public Customer getCustomerDetails(int CustomerId) {
        return null;
    }
}
