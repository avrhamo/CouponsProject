package com.aa.CouponsProject;

import com.aa.CouponsProject.utils.ArtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsProjectApplication.class, args);
		System.out.println("\033[1mCoupon system is running\033[0m");
	}

}
