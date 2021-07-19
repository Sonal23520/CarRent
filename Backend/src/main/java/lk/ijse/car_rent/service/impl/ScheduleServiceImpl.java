package lk.ijse.car_rent.service.impl;

import lk.ijse.car_rent.dto.CustomerDTO;
import lk.ijse.car_rent.dto.ScheduleDTO;
import lk.ijse.car_rent.entity.Customer;
import lk.ijse.car_rent.entity.Schedule;
import lk.ijse.car_rent.exception.ValidateException;
import lk.ijse.car_rent.repo.ScheduleRepo;
import lk.ijse.car_rent.service.ScheduleService;
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
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addSchedule(ScheduleDTO dto) {
        if (scheduleRepo.existsById(dto.getId())) {
            throw new ValidateException("Customer Already Exist");
        }
        scheduleRepo.save(mapper.map(dto, Schedule.class));
    }

    @Override
    public void deleteSchedule(String emil) {
        if (!scheduleRepo.existsById(emil)) {
            throw new ValidateException("No Schedule for Delete..!");
        }
        scheduleRepo.deleteById(emil);
    }

    @Override
    public ScheduleDTO searchSchedule(String email) {
        Optional<Schedule> schedule = scheduleRepo.findById(email);
        if (schedule.isPresent()) {
            return mapper.map(schedule.get(), ScheduleDTO.class);
        }
        return null;
    }

    @Override
    public ArrayList<ScheduleDTO> getAllSchedule() {
        List<Schedule> all = scheduleRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<ScheduleDTO>>() {
        }.getType());
    }

    @Override
    public void updateSchedule(ScheduleDTO dto) {

        if (scheduleRepo.existsById(dto.getId())) {
            scheduleRepo.save(mapper.map(dto, Schedule.class));

        }
    }

}
