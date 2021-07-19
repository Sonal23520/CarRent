package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.CustomerRepo;
import lk.ijse.car_rent.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getEmail())) {
            throw new ValidateException("Customer Already Exist");
        }
        customerRepo.save(mapper.map(dto, Customer.class));
    }

    @Override
    public void deleteCustomer(String emil) {
        if (!customerRepo.existsById(emil)) {
            throw new ValidateException("No Customer for Delete..!");
        }
        customerRepo.deleteById(emil);
    }

    @Override
    public CustomerDTO searchCustomer(String email) {
        Optional<Customer> customer = customerRepo.findById(email);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        List<Customer> all = customerRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {

        if (customerRepo.existsById(dto.getEmail())) {
            customerRepo.save(mapper.map(dto, Customer.class));

        }
    }

}
