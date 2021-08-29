package com.aa.CouponsProject.clr.test;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.services.CompanyService;
import com.aa.CouponsProject.services.CompanyServiceImpl;
import com.aa.CouponsProject.utils.ArtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(1)
@RequiredArgsConstructor
public class TestCompanyService implements CommandLineRunner {

    private final CompanyService companyService;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.TEST_COMPANY_SERVICE);

        Company company1 = Company.builder()
                .name("Crystal")
                .email("Crystal@Crystal.com")
                .password("123456")
                .build();

        Coupon coupon1 = Coupon.builder()
                .title("1+1")
                .amount(1000)
                .categoryId(Categories.FOOD)
                .description("one plus one")
                .company(company1)
                .price(1.5)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
//                .endDate(Date.valueOf(LocalDate.now().minusDays(25)))
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("2+2")
                .amount(1000)
                .categoryId(Categories.FOOD)
                .description("two plus two")
                .company(company1)
                .price(2.5)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
//                .endDate(Date.valueOf(LocalDate.now().minusDays(25)))
                .build();

        System.out.println("Testing login with - \"Crystal@Crystal.com\", \"123456\"");
        System.out.println( ((CompanyServiceImpl) companyService).login("Crystal@Crystal.com", "123456"));

        System.out.println("Trying to add already used coupon name to company ");

        Coupon c1 = couponRepository.getById(3);
        Coupon c2 = couponRepository.getById(4);
        Coupon c3 = couponRepository.getById(5);

        c1.setId(7);
        c1.setTitle("coupon1");

        c2.setId(8);
        c2.setTitle("coupon2");

        c3.setId(9);
        c3.setTitle("coupon3");


        try{
            companyService.addCoupon(c1);
            companyService.addCoupon(c2);
            companyService.addCoupon(c3);
        }
        catch(CouponSystemCustomExceptions e) {
            System.out.println(e.getMessage());
        }
//        companyService.getCompanyCoupons(Categories.FOOD).forEach(System.out::println);
        companyService.getCompanyCoupons(Categories.VACATION).forEach(System.out::println);

//        companyService.getCompanyCoupons().forEach(System.out::println);
//        companyService.addCoupon(coupon1);



    }
}
