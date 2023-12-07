package me.winiarski.mydojo_pl.entities.venue;

import me.winiarski.mydojo_pl.entities.trainingGroup.TrainingGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "venues")
@Getter
@Setter
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String adress;

    @OneToMany
    private List<TrainingGroup> groups;


}