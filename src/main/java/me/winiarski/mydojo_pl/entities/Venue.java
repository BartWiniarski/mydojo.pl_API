package me.winiarski.mydojo_pl.entities;

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

    @OneToMany(mappedBy = "venue")
    private List<TrainingGroup> trainingGroups;
}
