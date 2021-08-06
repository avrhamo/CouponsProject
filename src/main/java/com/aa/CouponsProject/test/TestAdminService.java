package com.aa.CouponsProject.test;


import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.services.AdminService;
import com.aa.CouponsProject.services.AdminServiceImpl;
import com.aa.CouponsProject.services.ClientService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class TestAdminService {

    @Autowired
    private static AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
    //private static ClientService adminServiceImpl = new AdminServiceImpl();

    public static void runTest() throws CouponSystemCustomExceptions {

        System.out.println("\n\n");

        System.out.println("Trying to login as admin with wrong email - > TestAdminService.login(\"bla-bla\", \"admin\") - answer:");
        System.out.println(adminServiceImpl.login("bla-bla", "admin") + "\n\n");

        System.out.println("Trying to login as admin with wrong email - > TestAdminService.login(\"admin@admin.com\", \"admin\") - answer:");
        System.out.println(adminServiceImpl.login("admin@admin.com", "admin") + "\n\n");

        System.out.println("creating company and adding to DB");
        Company company1 = Company.builder()
                .name("Prigat")
                .email("Prigat@Prigat.com")
                .password("123456")
                .build();
        System.out.println("company to add: " + company1.toString());
        adminServiceImpl.addCompany(company1);
//        System.out.println("select all companies: ");
//        adminServiceImpl.getAllCompanies().forEach(System.out::println);

    }

}
