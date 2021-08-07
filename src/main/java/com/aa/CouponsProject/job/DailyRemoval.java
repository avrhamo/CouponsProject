package com.aa.CouponsProject.job;

import com.aa.CouponsProject.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class DailyRemoval {

    private final CouponRepository couponRepository;

    //@Scheduled(fixedRate = 1000*60*60*24)
    @Scheduled(fixedRate = 1000*5)
    public void deleteExpiredCoupons() {
        //couponRepository.deleteAll();
        System.out.println("Daily job has finished");
    }
}
