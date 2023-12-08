package me.winiarski.mydojo_pl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToMany(mappedBy = "participants")
    private List<Event> eventsAsParticipant;

    @ManyToMany(mappedBy = "organizers")
    private List<Event> eventsAsOrganizer;

    @ManyToMany(mappedBy = "trainers")
    private List<TrainingGroup> trainingGroupsAsTrainer;

    @ManyToMany(mappedBy = "students")
    private List<TrainingGroup> trainingGroupsAsStudent;

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = true)
    private User guardian;

    @OneToMany(mappedBy = "guardian")
    private List<User> wards;

}
