package me.winiarski.mydojo_pl.entities.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dob;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
