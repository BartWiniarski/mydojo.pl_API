package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.ScheduleDTO;
import pl.mydojo.app.dto.ScheduleDTOMapper;
import pl.mydojo.app.entities.Schedule;
import pl.mydojo.app.repositories.ScheduleRepository;
import pl.mydojo.app.repositories.TrainingGroupRepository;
import pl.mydojo.app.repositories.VenueRepository;
import pl.mydojo.exceptions.schedule.ScheduleNotFoundException;
import pl.mydojo.exceptions.trainingGroup.TrainingGroupNotFoundException;
import pl.mydojo.exceptions.venue.VenueNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDTOMapper scheduleDTOMapper;
    private final VenueRepository venueRepository;
    private final TrainingGroupRepository trainingGroupRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleDTOMapper scheduleDTOMapper,
                           VenueRepository venueRepository,
                           TrainingGroupRepository trainingGroupRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleDTOMapper = scheduleDTOMapper;
        this.venueRepository = venueRepository;
        this.trainingGroupRepository = trainingGroupRepository;
    }

    //------------------ CRUD ------------------\\

    public List<ScheduleDTO> getSchedules() {

        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(s -> scheduleDTOMapper.apply(s))
                .collect(Collectors.toList());
    }

    public ScheduleDTO getScheduleById(long id) {

        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException(id);
        }

        Schedule schedule = scheduleRepository.findScheduleById(id);

        return scheduleDTOMapper.apply(schedule);
    }

    public Schedule addNewSchedule(ScheduleDTO scheduleDTO) {

        Schedule schedule = Schedule.builder()
                .dayOfWeek(scheduleDTO.getDayOfWeek())
                .time(scheduleDTO.getTime())
                .venue(venueRepository.findById(scheduleDTO.getVenueId())
                        .orElseThrow(() ->
                                new VenueNotFoundException(scheduleDTO.getVenueId())))
                .trainingGroup(trainingGroupRepository.findById(scheduleDTO.getTrainingGroupId())
                        .orElseThrow(() ->
                                new TrainingGroupNotFoundException(scheduleDTO.getTrainingGroupId())))
                .build();

        return scheduleRepository.save(schedule);
    }

    public void updateScheduleById(long id, ScheduleDTO scheduleDTO) {

        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException(id);
        }

        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (scheduleDTO.getDayOfWeek() != null) {
            schedule.setDayOfWeek(scheduleDTO.getDayOfWeek());
        }
        if (scheduleDTO.getTime() != null) {
            schedule.setTime(scheduleDTO.getTime());
        }
        if (scheduleDTO.getVenueId() != 0) {
            schedule.setVenue(
                    venueRepository.findById(scheduleDTO.getVenueId())
                            .orElseThrow(() -> new VenueNotFoundException(scheduleDTO.getVenueId())));
        }
        if (scheduleDTO.getTrainingGroupId() != 0) {
            schedule.setTrainingGroup(
                    trainingGroupRepository.findById(scheduleDTO.getTrainingGroupId())
                            .orElseThrow(() -> new TrainingGroupNotFoundException(scheduleDTO.getTrainingGroupId())));
        }
        scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(Long id) {

        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException(id);
        }
        scheduleRepository.deleteById(id);
    }
}
