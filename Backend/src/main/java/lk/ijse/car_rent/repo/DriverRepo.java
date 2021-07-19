package lk.ijse.car_rent.repo;

import lk.ijse.car_rent.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver,String> {
}
