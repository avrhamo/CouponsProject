package com.aa.CouponsProject.repos;

import com.aa.CouponsProject.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {


}
