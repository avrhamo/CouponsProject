package com.aa.CouponsProject.clients;

import com.aa.CouponsProject.exceptions.CouponSystemCustomExceptions;
import com.aa.CouponsProject.exceptions.ErrMsg;
import com.aa.CouponsProject.services.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class LoginManager {

    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemCustomExceptions {
        switch (clientType) {
            case ADMIN:
                ((AdminServiceImpl) adminService).login(email, password);
                return (ClientService) adminService;

            case COMPANY:
                ((CompanyServiceImpl) companyService).login(email, password);
                return (ClientService) companyService;

            case CUSTOMER:
                ((CustomerServiceImpl) customerService).login(email, password);
                return (ClientService) customerService;

            default:
                throw new CouponSystemCustomExceptions(ErrMsg.WRONG_LOGIN_DETAILS);

        }
    }
}
