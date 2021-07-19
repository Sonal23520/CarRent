package lk.ijse.car_rent.controller;


import lk.ijse.car_rent.service.LoginService;
import lk.ijse.car_rent.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService login;
    @GetMapping("/{email}/{password}")
    public ResponseEntity login(@PathVariable String email,@PathVariable String password){
        Enum anEnum = login.checkCustomer(email, password);
        return new ResponseEntity(new StandradResponse("200", "Done", anEnum), HttpStatus.OK);

    }
    @GetMapping("/{email}")
    public ResponseEntity find(@PathVariable String email){
        boolean reslut=login.findCustomer(email);
        return new ResponseEntity(new StandradResponse("200","Done",reslut),HttpStatus.OK);
    }

    @GetMapping("/admin/{email}/{password}")
    public ResponseEntity admin(@PathVariable String email,@PathVariable String password){
        Enum anEnum = login.checkAdmin(email, password);
        return new ResponseEntity(new StandradResponse("200", "Done", anEnum), HttpStatus.OK);
    }
}
