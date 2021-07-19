package lk.ijse.car_rent.dto;

import lk.ijse.car_rent.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDTO {
    private String id;
    private String start_time;
    private String end_time;
    private Driver driver;
}
