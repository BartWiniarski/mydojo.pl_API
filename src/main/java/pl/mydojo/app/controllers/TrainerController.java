package pl.mydojo.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.services.TrainingGroupService;
import pl.mydojo.security.jwt.JwtService;

import java.util.List;

@RestController
@RequestMapping("api/v1/trainer")
public class TrainerController {

    private final JwtService jwtService;
    private final TrainingGroupService trainingGroupService;

    public TrainerController(JwtService jwtService,
                             TrainingGroupService trainingGroupService){
        this.jwtService = jwtService;
        this.trainingGroupService = trainingGroupService;
    }

    @GetMapping("/trainingGroups")
    public List<TrainingGroupDTO> getTrainingGroups(@RequestHeader("Authorization") String token) {
        String userEmailFromToken = jwtService.extractUsername(token.substring(7));
        return trainingGroupService.getTrainerTrainingGroups(userEmailFromToken);
    }
}
