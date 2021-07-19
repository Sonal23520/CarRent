package lk.ijse.car_rent.controller;

import lk.ijse.car_rent.dto.AdminDTO;
import lk.ijse.car_rent.exception.NotFoundException;
import lk.ijse.car_rent.service.AdminService;
import lk.ijse.car_rent.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveAdmin(@RequestBody AdminDTO dto) {
        if (dto.getEmail().trim().length() <= 0) {
            throw new NotFoundException("Admin emil cannot   be empty");
        }
        service.addAdmin(dto);
        return new ResponseEntity(new StandradResponse("201", "Done", dto), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllAdmin() {
        ArrayList<AdminDTO> allAdmin = service.getAllAdmin();
        return new ResponseEntity(new StandradResponse("200", "Done", allAdmin), HttpStatus.OK);
    }

    @GetMapping(path = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchCustomer(@PathVariable String email) {
        AdminDTO adminDTO = service.searchAdmin(email);
        return new ResponseEntity(new StandradResponse("200", "Done", adminDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"email"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCustomer(@RequestParam String email) {
        service.deleteAdmin(email);
        return new ResponseEntity(new StandradResponse("200", "Done", null), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomer(@RequestBody AdminDTO dto) {
        if (dto.getEmail().trim().length() <= 0) {
            throw new NotFoundException("No id provided to update");
        }
        service.updateAdmin(dto);
        return new ResponseEntity(new StandradResponse("200", "Done", dto), HttpStatus.OK);
    }
}
