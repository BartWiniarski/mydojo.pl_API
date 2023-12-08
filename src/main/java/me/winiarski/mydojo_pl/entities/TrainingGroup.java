package me.winiarski.mydojo_pl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "trainingGroups")
@Getter
@Setter
public class TrainingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalTime date;

    @ManyToMany(mappedBy = "trainingGroups")
    private List<Trainer> trainers;

    @ManyToMany(mappedBy = "trainingGroups")
    private List<Student> students;

    @ManyToMany(mappedBy = "trainingGroups")
    private List<TrainingPlan> trainingPlans;

    @ManyToOne
    private Venue venue;

}
