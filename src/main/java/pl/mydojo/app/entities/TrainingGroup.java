package pl.mydojo.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;


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
    private LocalTime date;

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

    @ManyToOne
    private Venue venue;

}
