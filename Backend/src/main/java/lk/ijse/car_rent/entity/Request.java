package lk.ijse.car_rent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {
    @Id
    private String reqid;
    private String status;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Car car;
    @OneToOne
    private Schedule schedule;


}
