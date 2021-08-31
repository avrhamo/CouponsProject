package com.aa.CouponsProject.clr.test;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.clients.ClientType;
import com.aa.CouponsProject.clients.LoginManager;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.services.CustomerService;
import com.aa.CouponsProject.utils.ArtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class TestCustomerService implements CommandLineRunner {

    private CustomerService customerService;
    private final CouponRepository couponRepository;
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.TEST_CUSTOMER_SERVICE);

        try {
            System.out.println("Trying to login as customer with wrong email - > ((CustomerServiceImpl) customerService).login(\"bla-bla\", \"admin\") - answer:");
//            System.out.println(((CustomerServiceImpl) customerService).login("bla-bla", "admin") + "\n\n");
            customerService = (CustomerService) loginManager.login("bla-bla", "admin", ClientType.CUSTOMER);
        } catch (CouponSystemCustomExceptions e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\nTrying to login as customer with correct email - > ((CustomerServiceImpl) customerService).login(\"DaveChapplle@gmail.com\", \"123456\") - answer:");
//        System.out.println(((CustomerServiceImpl) customerService).login("DaveChapplle@gmail.com", "123456") + "\n\n");
        customerService = (CustomerService) loginManager.login("DaveChapplle@gmail.com", "123456", ClientType.CUSTOMER);

        Coupon coupon1 = couponRepository.getById(1);

        System.out.println("Buying coupon_id = 1 for customer_id = 1");
        System.out.println("before buying coupon : ");
        customerService.getAllCoupons(1).forEach(System.out::println);
        customerService.AddCouponPurchase(coupon1);
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

        try {
            customerService.AddCouponPurchase(couponRepository.getById(5));
        } catch (CouponSystemCustomExceptions e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\nGet all customer coupons by category - VACATION");
        customerService.getCouponsByCategory(1, Categories.VACATION).forEach(System.out::println);

        System.out.println("\n\nGet all coupons under 50$");
        customerService.getAllCouponsUpToMaxPrice(50).forEach(System.out::println);

        System.out.println("\n\nGet customer details");
        System.out.println(customerService.getCustomerDetails(1).toString());

    }
}
