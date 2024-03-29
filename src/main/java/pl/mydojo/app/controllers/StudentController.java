package pl.mydojo.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mydojo.app.dto.StudentTrainingGroupDTO;
import pl.mydojo.app.dto.StudentTrainingGroupDTOMapper;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.services.TrainingGroupService;
import pl.mydojo.security.jwt.JwtService;

import java.util.List;

@RestController
@RequestMapping("v1/student")
public class StudentController {

    private final JwtService jwtService;
    private final TrainingGroupService trainingGroupService;

    public StudentController(JwtService jwtService,
                             TrainingGroupService trainingGroupService) {
        this.jwtService = jwtService;
        this.trainingGroupService = trainingGroupService;
    }

    @GetMapping("/trainingGroups")
    public List<StudentTrainingGroupDTO> getTrainingGroups(@RequestHeader("Authorization") String token) {
        String userEmailFromToken = jwtService.extractUsername(token.substring(7));
        return trainingGroupService.getStudentTrainingGroups(userEmailFromToken);
    }
}
