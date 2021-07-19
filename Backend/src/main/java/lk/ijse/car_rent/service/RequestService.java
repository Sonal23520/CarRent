package lk.ijse.car_rent.service;

import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.dto.RequestDTO;

import java.util.ArrayList;

public interface RequestService {
    void addRequest(RequestDTO dto);

    void deleteRequest(String id);

    RequestDTO searchRequest(String id);

    ArrayList<RequestDTO> getAllRequest();

    void updateRequest(RequestDTO dto);

}
