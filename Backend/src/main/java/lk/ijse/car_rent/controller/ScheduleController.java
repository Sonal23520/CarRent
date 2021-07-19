package lk.ijse.car_rent.controller;

import lk.ijse.car_rent.dto.ScheduleDTO;
import lk.ijse.car_rent.exception.NotFoundException;

import lk.ijse.car_rent.service.ScheduleService;
import lk.ijse.car_rent.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveSchedule(@RequestBody ScheduleDTO dto) {
        System.out.println("okkk");
        if (dto.getId().trim().length() <= 0) {
            throw new NotFoundException("Schedule id cannot be empty");
        }
        service.addSchedule(dto);
        return new ResponseEntity(new StandradResponse("201", "Done", dto), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllSchedule() {
        ArrayList<ScheduleDTO> allSchedule = service.getAllSchedule();
        return new ResponseEntity(new StandradResponse("200", "Done", allSchedule), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchSchedule(@PathVariable String id) {
        ScheduleDTO scheduleDTO = service.searchSchedule(id);
        return new ResponseEntity(new StandradResponse("200", "Done", scheduleDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSchedule(@RequestParam String id) {
        service.deleteSchedule(id);
        return new ResponseEntity(new StandradResponse("200", "Done", null), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSchedule(@RequestBody ScheduleDTO dto) {
        if (dto.getId().trim().length() <= 0) {
            throw new NotFoundException("No id provided to update");
        }
        service.updateSchedule(dto);
        return new ResponseEntity(new StandradResponse("200", "Done", dto), HttpStatus.OK);
    }
}
