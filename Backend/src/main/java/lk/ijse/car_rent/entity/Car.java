package lk.ijse.car_rent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Car {
    @Id
    private String id;
    private String name;
    private int daily_rate;
    private int free_km_for_day;
    private int monthly_rate;
    private int free_km_for_month;
    private int price_per_extra_km;

    @JsonBackReference
    @OneToOne(mappedBy = "car")
    private Request request;

}
