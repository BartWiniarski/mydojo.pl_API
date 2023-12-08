package me.winiarski.mydojo_pl.entities.trainingPlan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.winiarski.mydojo_pl.entities.person.Trainer;
import me.winiarski.mydojo_pl.entities.trainingGroup.TrainingGroup;

import java.util.List;

@Entity
@Table(name = "trainingPlans")
@Getter
@Setter
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Trainer owner;

    @ManyToMany
    @JoinTable(
            name = "trainingGroups_trainingPlans",
            joinColumns = @JoinColumn(name = "trainingPlan_id"),
            inverseJoinColumns = @JoinColumn(name = "trainingGroup_id")
    )
    List<TrainingGroup> trainingGroups;
}
