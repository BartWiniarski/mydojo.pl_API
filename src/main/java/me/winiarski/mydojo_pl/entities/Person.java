package me.winiarski.mydojo_pl.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dob;

    //TODO : ustawić getter age obliczający wiek na podstawie dob
    @Transient
    private int age;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;
}
