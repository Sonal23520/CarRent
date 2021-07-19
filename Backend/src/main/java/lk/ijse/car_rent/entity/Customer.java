package lk.ijse.car_rent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    private String email;
    private String password;
    private String nic;
    private String license;
    private String address;
    private int tel;

    @JsonBackReference
    @OneToOne(mappedBy = "customer")
    private Request request;
}
