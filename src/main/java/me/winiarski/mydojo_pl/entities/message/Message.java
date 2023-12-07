package me.winiarski.mydojo_pl.entities.message;

import me.winiarski.mydojo_pl.entities.person.Student;
import me.winiarski.mydojo_pl.entities.person.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Trainer sender;

    @ManyToMany(mappedBy = "messages")
    private List<Student> receivers;

}
