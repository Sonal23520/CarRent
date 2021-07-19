package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.entity.Admin;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.AdminRepo;
import lk.ijse.car_rent.repo.CustomerRepo;
import lk.ijse.car_rent.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AdminRepo adminRepo;

    enum result{CUSTOMERNOTFOUND,CUSTOMERDETAILWRONG,CUSTOMERDETAILRIGHT}
    enum admin_result{ADMINNOTFOUND,ADMINDETAILWRONG,ADMINDETAILRIGHT}

    @Override
    public Enum checkCustomer(String email, String password) {
        Optional<Customer> customer = customerRepo.findById(email);
        if (customer.isPresent()){
            Customer user = customer.get();
            if (user.getPassword().equals(password.trim())){
                return result.CUSTOMERDETAILRIGHT;

            }else {
                return result.CUSTOMERDETAILWRONG;
            }
        }

        return result.CUSTOMERNOTFOUND;
    }

    @Override
    public Enum checkAdmin(String email, String password) {
        Optional<Admin> admin = adminRepo.findById(email);
        if (admin.isPresent()){
            Admin adminResult = admin.get();
            if (adminResult.getPassword().equals(password.trim())){
                return admin_result.ADMINDETAILRIGHT;
            }else {
                return admin_result.ADMINDETAILWRONG;
            }
        }
        return admin_result.ADMINNOTFOUND;
    }

    @Override
    public boolean findCustomer(String email) {
        return customerRepo.existsById(email);
    }
}
