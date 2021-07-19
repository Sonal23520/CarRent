package lk.ijse.car_rent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {
    private String email;
    private String password;
    private String nic;
    private String license;
    private String address;
    private int tel;

}
