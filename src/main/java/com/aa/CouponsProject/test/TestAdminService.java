package com.aa.CouponsProject.test;


import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Customer;
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

    //@Autowired
    //private static AdminServiceImpl adminServiceImpl;
    //private static ClientService adminServiceImpl = new AdminServiceImpl();

    public static void runAdminServiceImpl(AdminServiceImpl adminServiceImpl) throws CouponSystemCustomExceptions {

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
        System.out.println("select all companies: ");
        adminServiceImpl.getAllCompanies().forEach(System.out::println);

        company1 = adminServiceImpl.getSingleCompany(4);
        System.out.println("\n\nTrying to update a company with an id that do not exist: ");
        System.out.println("adminServiceImpl.updateCompany(7,company1);");
        adminServiceImpl.updateCompany(7,company1);


        System.out.println("\n\nTrying to update company Id: ");
        System.out.println("adminServiceImpl.updateCompany(2,company1);");
        adminServiceImpl.updateCompany(2,company1);

        System.out.println("\n\nTrying to update company name: ");
        company1.setName("bla-bla");
        System.out.println("company1.setName(\"bla-bla\")");
        System.out.println("adminServiceImpl.updateCompany(4,company1);");
        adminServiceImpl.updateCompany(4,company1);

        System.out.println("\n\nTrying to update company password: ");
        System.out.println("company from DB before changes:");
        company1 = adminServiceImpl.getSingleCompany(company1.getId());
        System.out.println(company1);
        System.out.println("company1.setPassword(\"112233\");");
        company1.setPassword("112233");
        adminServiceImpl.updateCompany(company1.getId(),company1);
        System.out.println("company from DB after changes");
        System.out.println(adminServiceImpl.getSingleCompany(company1.getId()).toString());

        System.out.println("\n\n-----Testing Customer - Admin service -----");

        System.out.println("Get single customer by id");
        Customer customer1 = adminServiceImpl.getSingleCustomer(1);
        System.out.println("Customer customer1 = adminServiceImpl.getSingleCustomer(1);");
        System.out.println(customer1.toString());

        System.out.println("\n\nTrying to add new customer");
        System.out.println("Customer customer2 = Customer.builder()\n" +
                "                .firstName(\"Asaf\")\n" +
                "                .lastName(\"Cohen\")\n" +
                "                .email(\"AsafCohen@gmail.com\")\n" +
                "                .password(\"123456\")\n" +
                "                .build();\n" +
                "        ");
        Customer customer2 = Customer.builder()
                .firstName("Asaf")
                .lastName("Cohen")
                .email("AsafCohen@gmail.com")
                .password("123456")
                .build();
        adminServiceImpl.addCustomer(customer2);
        System.out.println("Customer from DB");
        System.out.println(adminServiceImpl.getSingleCustomer(4).toString());

        customer2.setPassword("POPOPOPO");
        adminServiceImpl.updateCustomer(customer2.getId(), customer2);

        adminServiceImpl.deleteCustomer(customer2.getId());


    }

}
