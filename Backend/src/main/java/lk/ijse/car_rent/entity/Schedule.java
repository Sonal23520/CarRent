package lk.ijse.car_rent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {
    @Id
    private String id;
    private String start_time;
    private String end_time;

    @OneToOne
    private Driver driver;

    @JsonBackReference
    @OneToOne(mappedBy = "schedule")
    private Request request;

}
