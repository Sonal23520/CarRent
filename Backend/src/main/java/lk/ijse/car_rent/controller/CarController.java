package lk.ijse.car_rent.controller;

import lk.ijse.car_rent.dto.CarDTO;
import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.exception.NotFoundException;
import lk.ijse.car_rent.service.CarService;
import lk.ijse.car_rent.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveCustomer(@RequestBody CarDTO dto) {
        if (dto.getId().trim().length() <= 0) {
            throw new NotFoundException("Car id cannot   be empty");
        }
        service.addCar(dto);
        return new ResponseEntity(new StandradResponse("201", "Done", dto), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCar() {
        ArrayList<CarDTO> allCar = service.getAllCar();
        return new ResponseEntity(new StandradResponse("200", "Done", allCar), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchCar(@PathVariable String id) {
        CarDTO carDTO = service.searchCar(id);
        return new ResponseEntity(new StandradResponse("200", "Done", carDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCar(@RequestParam String id) {
        service.deleteCar(id);
        return new ResponseEntity(new StandradResponse("200", "Done", null), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCar(@RequestBody CarDTO dto) {
        if (dto.getId().trim().length() <= 0) {
            throw new NotFoundException("No id provided to update");
        }
        service.updateCar(dto);
        return new ResponseEntity(new StandradResponse("200", "Done", dto), HttpStatus.OK);
    }

}
