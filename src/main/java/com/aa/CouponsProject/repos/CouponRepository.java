package com.aa.CouponsProject.repos;

import com.aa.CouponsProject.beans.Categories;
import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `coupon-sys`.coupons WHERE company_id = :companyId", nativeQuery = true)
    void deleteCouponByCompanyId1(@Param("companyId") int companyId);

    boolean existsByCompanyIdAndTitle(int CompanyId, String title);

    Company getByCompanyId(int id);

//    @Query(value = "SELECT * `coupon-sys`.coupons WHERE company_id = :companyId", nativeQuery = true)
//    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getAllByCompanyId(int companyId);

    List<Coupon> getAllByCompanyIdAndCategoryId(int companyId, Categories category);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `coupon-sys`.customers_coupons WHERE coupons_id = :couponId", nativeQuery = true)
    void deleteCouponFromCustomers(@Param("couponId") int couponId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `coupon-sys`.customers_coupons WHERE customer_id = :customerId", nativeQuery = true)
    void deleteCustomerCoupons(@Param("customerId") int customerId);


    @Query(value = "SELECT * `coupon-sys`.coupons WHERE customer_id = :customerId", nativeQuery = true)
    List<Coupon> getCustomerCoupons(@Param("customerId") int customerId);
}
