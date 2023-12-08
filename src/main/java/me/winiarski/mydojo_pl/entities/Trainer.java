package me.winiarski.mydojo_pl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "trainers")
@Getter
@Setter
public class Trainer extends Person{

    public Trainer(){
        super();
    }

    @Column(columnDefinition = "boolean default false")
    private boolean admin;

    @ManyToMany
    @JoinTable(
            name = "trainingGroups_trainers",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "trainingGroup_id")
    )
    private List<TrainingGroup> trainingGroups;

    @ManyToMany(mappedBy = "organizers")
    private List<Event> events;
}
