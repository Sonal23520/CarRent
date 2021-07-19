package lk.ijse.car_rent.service;

import lk.ijse.car_rent.dto.CarDTO;
import lk.ijse.car_rent.dto.CustomerDTO;

import java.util.ArrayList;

public interface CarService {
    void addCar(CarDTO dto);

    void deleteCar(String id);

    CarDTO searchCar(String id);

    ArrayList<CarDTO> getAllCar();

    void updateCar(CarDTO dto);

}
