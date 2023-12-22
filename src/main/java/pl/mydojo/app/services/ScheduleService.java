package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.ScheduleDTO;
import pl.mydojo.app.dto.ScheduleDTOMapper;
import pl.mydojo.app.entities.Schedule;
import pl.mydojo.app.repositories.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDTOMapper scheduleDTOMapper;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleDTOMapper scheduleDTOMapper){
        this.scheduleRepository = scheduleRepository;
        this.scheduleDTOMapper = scheduleDTOMapper;
    }


    public List<ScheduleDTO> getSchedules() {
            List<Schedule> schedules = scheduleRepository.findAll();

            return schedules.stream()
                    .map(s-> scheduleDTOMapper.apply(s))
                    .collect(Collectors.toList());
    }
}
