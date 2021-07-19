package lk.ijse.car_rent.repo;

import lk.ijse.car_rent.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request,String> {
}
