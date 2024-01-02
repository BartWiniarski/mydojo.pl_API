package pl.mydojo.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.services.TrainingGroupService;
import pl.mydojo.security.jwt.JwtService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final JwtService jwtService;
    private final TrainingGroupService trainingGroupService;

    public StudentController(JwtService jwtService,
                             TrainingGroupService trainingGroupService){
        this.jwtService = jwtService;
        this.trainingGroupService = trainingGroupService;
    }

    @GetMapping("/test")
    public TestResponse testStudent(){
        String role = "student";
        LocalDateTime timeNow = LocalDateTime.now();
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                "sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Laoreet suspendisse interdum" +
                "consectetur libero id faucibus nisl tincidunt. Orci ac auctor augue mauris augue neque gravida in." +
                " Tristique magna sit amet purus gravida. Nunc sed id semper risus. Convallis osuere" +
                "morbi leo urna. Morbi tincidunt augue interdum velit euismod in. Tempus urna et" +
                "pharetra. Lacus viverra vitae congue eu consequat. Sed odio morbi quis commodo" +
                "Lacus sed viverra tellus in hac habitasse platea dictumst vestibulum. Posuere" +
                "sollicitudin aliquam ultrices sagittis orci a scelerisque purus. Elementum" +
                "egestas sed sed risus pretium quam. Tincidunt nunc pulvinar sapien et ligula";

        return TestResponse.builder()
                .role(role)
                .time(timeNow)
                .loremIpsum(loremIpsum)
                .build();
    }

    @GetMapping("/trainingGroups")
    public List<TrainingGroupDTO> getTrainingGroups(@RequestHeader("Authorization") String token) {
        String userEmailFromToken = jwtService.extractUsername(token.substring(7));
        return trainingGroupService.getStudentTrainingGroups(userEmailFromToken);
    }
}
