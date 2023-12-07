package me.winiarski.mydojo_pl.entities.person;

import me.winiarski.mydojo_pl.entities.event.Event;
import me.winiarski.mydojo_pl.entities.trainingGroup.TrainingGroup;
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

    @ManyToMany
    @JoinTable(
            name = "events_trainers",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "events_id")

    )
    private List<Event> events;
}
