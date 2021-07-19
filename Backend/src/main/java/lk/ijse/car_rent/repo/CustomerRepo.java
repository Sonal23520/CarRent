package lk.ijse.car_rent.repo;

import lk.ijse.car_rent.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, String> {

}
