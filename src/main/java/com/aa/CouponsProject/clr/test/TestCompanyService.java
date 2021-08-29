package com.aa.CouponsProject.clr.test;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.services.CompanyService;
import com.aa.CouponsProject.services.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(3)
@RequiredArgsConstructor
public class TestCompanyService implements CommandLineRunner {

    private final CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {

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

        System.out.println("Testing login with - \"Crystal@Crystal.com\", \"123456\"");
        System.out.println( ((CompanyServiceImpl) companyService).login("Crystal@Crystal.com", "123456"));

        System.out.println("Trying to add already used coupon name to company ");

        try{
            companyService.addCoupon(coupon1);
        }
        catch(CouponSystemCustomExceptions e) {
            System.out.println(e.getMessage());
        }
//        companyService.addCoupon(coupon1);



    }
}
