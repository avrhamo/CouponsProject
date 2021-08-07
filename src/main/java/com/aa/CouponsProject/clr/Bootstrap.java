package com.aa.CouponsProject.clr;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.job.DailyRemoval;
import com.aa.CouponsProject.repos.CompanyRepository;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.repos.CustomerRepository;
import com.aa.CouponsProject.services.AdminServiceImpl;
import com.aa.CouponsProject.test.TestAdminService;
import com.aa.CouponsProject.utils.ArtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private final AdminServiceImpl adminServiceImpl;
    @Autowired
    private final CouponRepository couponRepository;
    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.BOOTSTRAP_1);

        System.out.println(ArtUtils.COMPANIES);
        Company company1 = Company.builder()
                .name("Crystal")
                .email("Crystal@Crystal.com")
                .password("123456")
                .build();

        Company company2 = Company.builder()
                .name("KSP")
                .email("KSP@KSP.com")
                .password("123456")
                .build();

        Company company3 = Company.builder()
                .name("AviTours")
                .email("AviTours@AviTours.com")
                .password("123456")
                .build();

        System.out.println(ArtUtils.INSERT);
        companyRepository.saveAll(Arrays.asList(company1, company2, company3));

        System.out.println(ArtUtils.GET_ALL);
        companyRepository.findAll().forEach(System.out::println);

        System.out.println(ArtUtils.CUSTOMERS);
        Customer customer1 = Customer.builder()
                .firstName("Dave")
                .lastName("Chapplle")
                .email("DaveChapplle@gmail.com")
                .password("123456")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Eddie")
                .lastName("Murphy")
                .email("EddieMurphy@gmail.com")
                .password("123456")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("Leonel")
                .lastName("Messi")
                .email("LeonelMessi@gmail.com")
                .password("123456")
                .build();

        System.out.println(ArtUtils.INSERT);
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));

        System.out.println(ArtUtils.GET_ALL);
        customerRepository.findAll().forEach(System.out::println);

        System.out.println(ArtUtils.COUPONS);
        Coupon coupon1 = Coupon.builder()
                .title("1+1")
                .amount(1000)
                .categoryId(Categories.FOOD)
                .description("one plus one")
                .company(companyRepository.getById(1))
                .price(1.5)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("10%")
                .amount(100)
                .categoryId(Categories.PC)
                .description("10% off")
                .company(companyRepository.getById(2))
                .price(25)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("freeDay")
                .amount(1000)
                .categoryId(Categories.VACATION)
                .description("another day on the house")
                .company(companyRepository.getById(3))
                .price(105)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        System.out.println(ArtUtils.INSERT);
        couponRepository.saveAll(Arrays.asList(coupon1, coupon2, coupon3 ));

        System.out.println(ArtUtils.GET_ALL);
        couponRepository.findAll().forEach(System.out::println);

        TestAdminService.runAdminServiceImpl(adminServiceImpl);
    }
}
