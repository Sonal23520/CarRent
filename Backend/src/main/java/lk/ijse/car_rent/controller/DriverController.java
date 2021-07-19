package lk.ijse.car_rent.controller;

import lk.ijse.car_rent.dto.DriverDTO;
import lk.ijse.car_rent.exception.NotFoundException;
import lk.ijse.car_rent.service.DriverService;
import lk.ijse.car_rent.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveDriver(@RequestBody DriverDTO dto) {
        if (dto.getEmail().trim().length() <= 0) {
            throw new NotFoundException("Driver emil cannot   be empty");
        }
        service.addDriver(dto);
        return new ResponseEntity(new StandradResponse("201", "Done", dto), HttpStatus.CREATED);
    }
    /////////////////////////////////////File Upload//////////////////////////////////////////
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveFile(@RequestPart("nic") MultipartFile nic, @RequestPart("license") MultipartFile license) {
        System.out.println(nic.getOriginalFilename());
        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/Driver_uploads");
            uploadsDir.mkdir();
            nic.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + nic.getOriginalFilename()));
            license.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + license.getOriginalFilename()));
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllDriver() {
        ArrayList<DriverDTO> allDriver = service.getAllDriver();
        return new ResponseEntity(new StandradResponse("200", "Done", allDriver), HttpStatus.OK);
    }

    @GetMapping(path = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchDriver(@PathVariable String email) {
        DriverDTO driverDTO = service.searchDriver(email);
        return new ResponseEntity(new StandradResponse("200", "Done", driverDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"email"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteDriver(@RequestParam String email) {
        service.deleteDriver(email);
        return new ResponseEntity(new StandradResponse("200", "Done", null), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomer(@RequestBody DriverDTO dto) {
        if (dto.getEmail().trim().length() <= 0) {
            throw new NotFoundException("No id provided to update");
        }
        service.updateDriver(dto);
        return new ResponseEntity(new StandradResponse("200", "Done", dto), HttpStatus.OK);
    }
}
