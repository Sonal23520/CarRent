package lk.ijse.car_rent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO {
    private String id;
    private String name;
    private int daily_rate;
    private int free_km_for_day;
    private int monthly_rate;
    private int free_km_for_month;
    private int price_per_extra_km;
}
