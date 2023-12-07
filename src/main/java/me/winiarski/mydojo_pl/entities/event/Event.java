package me.winiarski.mydojo_pl.entities.event;

import me.winiarski.mydojo_pl.entities.person.Student;
import me.winiarski.mydojo_pl.entities.person.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")
@Setter
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Trainer owner;

    @ManyToMany(mappedBy = "events")
    private List<Student> participants;

    @ManyToMany(mappedBy = "events")
    private List<Trainer> organizers;

}
