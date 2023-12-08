package me.winiarski.mydojo_pl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends Person{

    public Student(){
        super();
    }

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = true)
    private Student guardian;

    @OneToMany(mappedBy = "guardian")
    private List<Student> wards;

    @ManyToMany
    @JoinTable(
            name = "trainingGroups_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "trainingGroup_id")
    )
    private List<TrainingGroup> trainingGroups;

    @ManyToMany
    @JoinTable(
            name = "events_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "events_id")

    )
    private List<Event> events;

    @ManyToMany
    @JoinTable(
            name = "messages_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "messages_id")
    )
    private List<Message> messages;
}
