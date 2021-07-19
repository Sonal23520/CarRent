package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.dto.DriverDTO;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.entity.Driver;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.DriverRepo;
import lk.ijse.car_rent.service.DriverService;
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
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addDriver(DriverDTO dto) {
        if (driverRepo.existsById(dto.getEmail())) {
            throw new ValidateException("Driver Already Exist");
        }
        driverRepo.save(mapper.map(dto, Driver.class));
    }

    @Override
    public void deleteDriver(String emil) {
        if (!driverRepo.existsById(emil)) {
            throw new ValidateException("No Driver for Delete..!");
        }
        driverRepo.deleteById(emil);
    }

    @Override
    public DriverDTO searchDriver(String email) {
        Optional<Driver> driver = driverRepo.findById(email);
        if (driver.isPresent()) {
            return mapper.map(driver.get(), DriverDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<DriverDTO> getAllDriver() {
        List<Driver> all = driverRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<DriverDTO>>() {
        }.getType());
    }

    @Override
    public void updateDriver(DriverDTO dto) {

        if (driverRepo.existsById(dto.getEmail())) {
            driverRepo.save(mapper.map(dto, Driver.class));

        }
    }
}
