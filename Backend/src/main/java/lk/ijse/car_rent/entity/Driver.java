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
public class Driver {
    @Id
    private String email;
    private String password;
    private String nic;
    private String license;
    private String address;
    private int tel;

    @OneToOne(mappedBy = "driver")
    @JsonBackReference
    private Schedule schedule;
}
