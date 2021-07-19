package lk.ijse.car_rent.repo;

import lk.ijse.car_rent.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule,String> {
}
