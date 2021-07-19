package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.dto.CarDTO;
import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.entity.Car;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.CarRepo;
import lk.ijse.car_rent.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addCar(CarDTO dto) {
        if (carRepo.existsById(dto.getId())) {
            throw new ValidateException("Car Already Exist");
        }
        carRepo.save(mapper.map(dto, Car.class));
    }

    @Override
    public void deleteCar(String id) {
        if (!carRepo.existsById(id)) {
            throw new ValidateException("No Car for Delete..!");
        }
        carRepo.deleteById(id);
    }

    @Override
    public CarDTO searchCar(String id) {
        Optional<Car> car = carRepo.findById(id);
        if (car.isPresent()) {
            return mapper.map(car.get(), CarDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<CarDTO> getAllCar() {
        List<Car> all = carRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<CarDTO>>() {
        }.getType());
    }

    @Override
    public void updateCar(CarDTO dto) {

        if (carRepo.existsById(dto.getId())) {
            carRepo.save(mapper.map(dto, Car.class));

        }
    }
}
