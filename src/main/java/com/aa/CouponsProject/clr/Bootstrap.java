package com.aa.CouponsProject.clr;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.repos.CompanyRepository;
import com.aa.CouponsProject.repos.CouponRepository;
import com.aa.CouponsProject.repos.CustomerRepository;
import com.aa.CouponsProject.utils.ArtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Order(1)
public class Bootstrap implements CommandLineRunner {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
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
//                .endDate(Date.valueOf(LocalDate.now().minusDays(25)))
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("10% - off")
                .amount(100)
                .categoryId(Categories.PC)
                .description("10% off for all products")
                .company(companyRepository.getById(2))
                .price(25)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("FreeDay - Paris")
                .amount(1000)
                .categoryId(Categories.VACATION)
                .description("another day in Paris on the house")
                .company(companyRepository.getById(3))
                .price(105)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        Coupon coupon4 = Coupon.builder()
                .title("Eat at paris")
                .amount(1000)
                .categoryId(Categories.VACATION)
                .description("3 Dinners every day at paris")
                .company(companyRepository.getById(1))
                .price(105)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        Coupon coupon5 = Coupon.builder()
                .title("FreeDay - London")
                .amount(1000)
                .categoryId(Categories.VACATION)
                .description("Another day in London on the house")
                .company(companyRepository.getById(2))
                .price(105)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        Coupon coupon6 = Coupon.builder()
                .title("Free mouse")
                .amount(1000)
                .categoryId(Categories.PC)
                .description("Free mouse if your kart is > 50$")
                .company(companyRepository.getById(2))
                .price(5)
                .image("http://image.com")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .build();

        System.out.println(ArtUtils.INSERT);
        couponRepository.saveAll(Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6 ));

        System.out.println(ArtUtils.GET_ALL);
        couponRepository.findAll().forEach(System.out::println);

    }
}
