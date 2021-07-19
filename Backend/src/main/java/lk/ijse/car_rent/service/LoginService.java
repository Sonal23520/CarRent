package lk.ijse.car_rent.service;

public interface LoginService {
    Enum checkCustomer(String email,String password);
    Enum checkAdmin(String email,String password);
    boolean findCustomer(String email);
}
