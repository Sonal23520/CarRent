package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.dto.AdminDTO;
import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.entity.Admin;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.AdminRepo;
import lk.ijse.car_rent.service.AdminService;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addAdmin(AdminDTO dto) {
        if (adminRepo.existsById(dto.getEmail())) {
            throw new ValidateException("Admin Already Exist");
        }
        adminRepo.save(mapper.map(dto, Admin.class));
    }

    @Override
    public void deleteAdmin(String emil) {
        if (!adminRepo.existsById(emil)) {
            throw new ValidateException("No Admin for Delete..!");
        }
        adminRepo.deleteById(emil);
    }

    @Override
    public AdminDTO searchAdmin(String email) {
        Optional<Admin> admin = adminRepo.findById(email);
        if (admin.isPresent()) {
            return mapper.map(admin.get(), AdminDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<AdminDTO> getAllAdmin() {
        List<Admin> all = adminRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<AdminDTO>>() {
        }.getType());
    }

    @Override
    public void updateAdmin(AdminDTO dto) {

        if (adminRepo.existsById(dto.getEmail())) {
            adminRepo.save(mapper.map(dto, Admin.class));

        }
    }
}
