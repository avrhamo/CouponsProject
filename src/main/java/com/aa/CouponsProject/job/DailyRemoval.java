package com.aa.CouponsProject.job;

import com.aa.CouponsProject.beans.Coupon;
import com.aa.CouponsProject.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class DailyRemoval {

    private final CouponRepository couponRepository;

    //@Scheduled(fixedRate = 1000*60*60*24)
    @Scheduled(fixedRate = 1000*5)
    public void deleteExpiredCoupons() {
        List<Coupon> expiredCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
        couponRepository.deleteAll(expiredCoupons);
        System.out.println("Daily job has finished");
    }
}
