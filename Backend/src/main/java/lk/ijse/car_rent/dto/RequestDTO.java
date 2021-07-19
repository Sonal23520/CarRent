package lk.ijse.car_rent.dto;

import lk.ijse.car_rent.entity.Car;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {
    private String reqid;
    private String status;
    private Customer customer;
    private Car car;
    private Schedule schedule;
}
