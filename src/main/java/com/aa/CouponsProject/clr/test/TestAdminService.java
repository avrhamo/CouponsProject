package com.aa.CouponsProject.clr.test;


import com.aa.CouponsProject.beans.Company;
import com.aa.CouponsProject.beans.Customer;
import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.services.AdminService;
import com.aa.CouponsProject.services.AdminServiceImpl;
import com.aa.CouponsProject.services.ClientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class TestAdminService implements CommandLineRunner {

    private final AdminService adminService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n\n");

        System.out.println("Trying to login as admin with wrong email - > TestAdminService.login(\"bla-bla\", \"admin\") - answer:");
        System.out.println(((AdminServiceImpl) adminService).login("bla-bla", "admin") + "\n\n");

        System.out.println("Trying to login as admin with correct email - > TestAdminService.login(\"admin@admin.com\", \"admin\") - answer:");
        System.out.println(((AdminServiceImpl) adminService).login("admin@admin.com", "admin") + "\n\n");

        System.out.println("creating company and adding to DB");

        Company company1 = Company.builder()
                .name("Prigat")
                .email("Prigat@Prigat.com")
                .password("123456")
                .build();
        System.out.println("company to add: " + company1.toString());
        adminService.addCompany(company1);
        System.out.println("select all companies: ");
        adminService.getAllCompanies().forEach(System.out::println);

        company1 = adminService.getSingleCompany(4);
        System.out.println("\n\nTrying to update a company with an id that do not exist: ");
        System.out.println("adminServiceImpl.updateCompany(7,company1);");
        try {
            adminService.updateCompany(7,company1);
        }catch (CouponSystemCustomExceptions e ) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\nTrying to update company Id: ");
        System.out.println("adminServiceImpl.updateCompany(2,company1);");
        try {
            adminService.updateCompany(2,company1);
        }catch (CouponSystemCustomExceptions e ) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\nTrying to update company name: ");
        company1.setName("bla-bla");
        System.out.println("company1.setName(\"bla-bla\")");
        System.out.println("adminServiceImpl.updateCompany(4,company1);");
        try {
            adminService.updateCompany(4,company1);
        }catch (CouponSystemCustomExceptions e ) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n\nTrying to update company password: ");
        System.out.println("company from DB before changes:");
        company1 = adminService.getSingleCompany(company1.getId());
        System.out.println(company1);
        System.out.println("company1.setPassword(\"112233\");");
        company1.setPassword("112233");
        adminService.updateCompany(company1.getId(),company1);
        System.out.println("company from DB after changes");
        System.out.println(adminService.getSingleCompany(company1.getId()).toString());

        System.out.println("\n\n-----Testing Customer - Admin service -----");

        System.out.println("Get single customer by id");
        Customer customer1 = adminService.getSingleCustomer(1);
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
        adminService.addCustomer(customer2);
        System.out.println("Customer from DB");
        System.out.println(adminService.getSingleCustomer(4).toString());

        System.out.println("Updating customer 4 password to \"POPOPOPO\" :");
        customer2.setPassword("POPOPOPO");
        adminService.updateCustomer(customer2.getId(), customer2);
        System.out.println(adminService.getSingleCustomer(4).toString());

        System.out.println("\n\nDelete customer 4 from DB");
        System.out.println("Get all customers from DB before delete ");
        adminService.getAllCustomers().forEach(System.out::println);
        adminService.deleteCustomer(customer2.getId());
        System.out.println("Get all customers from DB after delete ");
        adminService.getAllCustomers().forEach(System.out::println);


    }
}
