package lk.ijse.car_rent.service;
import lk.ijse.car_rent.dto.CustomerDTO;
import java.util.ArrayList;
public interface CustomerService {

    void addCustomer(CustomerDTO dto);

    void deleteCustomer(String id);

    CustomerDTO searchCustomer(String id);

    ArrayList<CustomerDTO> getAllCustomers();

    void updateCustomer(CustomerDTO dto);

}