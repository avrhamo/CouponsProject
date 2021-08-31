package com.aa.CouponsProject.clr.test;


import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.services.CompanyService;
import com.aa.CouponsProject.services.CompanyServiceImpl;
import com.aa.CouponsProject.services.CustomerService;
import com.aa.CouponsProject.services.CustomerServiceImpl;
import com.aa.CouponsProject.utils.ArtUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(1)
@RequiredArgsConstructor
public class TestCustomerService implements CommandLineRunner {

    private final CustomerService customerService;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
//        if(3>0)return;
        System.out.println(ArtUtils.TEST_CUSTOMER_SERVICE);

        System.out.println("Trying to login as customer with wrong email - > ((CustomerServiceImpl) customerService).login(\"bla-bla\", \"admin\") - answer:");
        System.out.println(((CustomerServiceImpl) customerService).login("bla-bla", "admin") + "\n\n");

        System.out.println("\n\nTrying to login as customer with correct email - > ((CustomerServiceImpl) customerService).login(\"DaveChapplle@gmail.com\", \"123456\") - answer:");
        System.out.println(((CustomerServiceImpl) customerService).login("DaveChapplle@gmail.com", "123456") + "\n\n");

        Coupon coupon1 = couponRepository.getById(1);

        System.out.println("Buying coupon_id = 1 for customer_id = 1");
        System.out.println("before buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);
        customerService.AddCouponPurchase(couponRepository.getById(1));
        System.out.println("After buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);

        System.out.println("\nBuying coupon_id = 4 for customer_id = 1");
        System.out.println("before buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);
        customerService.AddCouponPurchase(couponRepository.getById(4));
        System.out.println("After buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);

        System.out.println("\nBuying coupon_id = 5 for customer_id = 1");
        System.out.println("before buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);
        customerService.AddCouponPurchase(couponRepository.getById(5));
        System.out.println("After buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);

        System.out.println("\n\nError Testing - Buying coupon_id = 5 for customer_id = 1 AGAIN!");

        try{
            customerService.AddCouponPurchase(couponRepository.getById(5));
        }catch (CouponSystemCustomExceptions e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\nGet all customer coupons by category - VACATION");
        customerService.getCouponsByCategory(1,Categories.VACATION).forEach(System.out::println);

        System.out.println("\n\nGet all coupons under 50$");
        customerService.getAllCouponsUpToMaxPrice(50).forEach(System.out::println);

        System.out.println("\n\nGet customer details");
        System.out.println(customerService.getCustomerDetails(1).toString());

    }
}
