package lk.ijse.car_rent.controller;

import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.dto.RequestDTO;
import lk.ijse.car_rent.exception.NotFoundException;
import lk.ijse.car_rent.service.RequestService;
import lk.ijse.car_rent.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveRequest(@RequestBody RequestDTO dto) {
        if (dto.getReqid().trim().length() <= 0) {
            throw new NotFoundException("Request id cannot   be empty");
        }
        service.addRequest(dto);
        return new ResponseEntity(new StandradResponse("201", "Done", dto), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllRequest() {
        ArrayList<RequestDTO> allRequest = service.getAllRequest();
        return new ResponseEntity(new StandradResponse("200", "Done", allRequest), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchRequest(@PathVariable String id) {
        RequestDTO requestDTO = service.searchRequest(id);
        return new ResponseEntity(new StandradResponse("200", "Done", requestDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteRequest(@RequestParam String id) {
        service.deleteRequest(id);
        return new ResponseEntity(new StandradResponse("200", "Done", null), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRequest(@RequestBody RequestDTO dto) {
        if (dto.getReqid().trim().length() <= 0) {
            throw new NotFoundException("No id provided to update");
        }
        service.updateRequest(dto);
        return new ResponseEntity(new StandradResponse("200", "Done", dto), HttpStatus.OK);
    }
}
