package lk.ijse.car_rent.repo;

import lk.ijse.car_rent.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,String> {
}
