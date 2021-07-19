package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.dto.RequestDTO;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.entity.Request;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.RequestRepo;
import lk.ijse.car_rent.service.RequestService;
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
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addRequest(RequestDTO dto) {
        if (requestRepo.existsById(dto.getReqid())) {
            throw new ValidateException("Request Already Exist");
        }
        requestRepo.save(mapper.map(dto, Request.class));
    }

    @Override
    public void deleteRequest(String id) {
        if (!requestRepo.existsById(id)) {
            throw new ValidateException("No Request for Delete..!");
        }
        requestRepo.deleteById(id);
    }

    @Override
    public RequestDTO searchRequest(String id) {
        Optional<Request> request = requestRepo.findById(id);
        if (request.isPresent()) {
            return mapper.map(request.get(), RequestDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<RequestDTO> getAllRequest() {
        List<Request> all = requestRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<RequestDTO>>() {
        }.getType());
    }

    @Override
    public void updateRequest(RequestDTO dto) {

        if (requestRepo.existsById(dto.getReqid())) {
            requestRepo.save(mapper.map(dto, Request.class));

        }
    }
}
