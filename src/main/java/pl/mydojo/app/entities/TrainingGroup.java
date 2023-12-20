package pl.mydojo.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "trainingGroups")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "trainingGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules;


    @ManyToMany
    @JoinTable(
            name = "training_groups_trainers",
            joinColumns = @JoinColumn(name = "training_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> trainers;

    @ManyToMany
    @JoinTable(
            name = "training_groups_students",
            joinColumns = @JoinColumn(name = "training_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> students;

    @ManyToMany(mappedBy = "trainingGroups")
    private List<TrainingPlan> trainingPlans;

}
