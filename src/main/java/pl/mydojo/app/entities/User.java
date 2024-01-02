package pl.mydojo.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    @Column(unique = true)
    private String email;
    private String password;
    @Builder.Default
    private Boolean locked = false;
    @Builder.Default
    private Boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> wards;


//------------------CONSTRUCTORS--------------------\\


    //student\\
    public User(String firstName, String lastName, LocalDate dob, int age, String email, String password, Boolean locked, Boolean enabled, List<Role> roles, List<Event> eventsAsParticipant, List<TrainingGroup> trainingGroupsAsStudent, User guardian, List<User> wards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.roles = roles;
        this.eventsAsParticipant = eventsAsParticipant;
        this.trainingGroupsAsStudent = trainingGroupsAsStudent;
        this.guardian = guardian;
        this.wards = wards;
    }

    //trainer\\
    public User(String firstName, String lastName, LocalDate dob, int age, String email, String password, Boolean locked, Boolean enabled, List<Role> roles, List<Event> eventsAsOrganizer, List<TrainingGroup> trainingGroupsAsTrainer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.roles = roles;
        this.eventsAsOrganizer = eventsAsOrganizer;
        this.trainingGroupsAsTrainer = trainingGroupsAsTrainer;
    }


//------------------SPRING SECURITY------------------\\

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType().name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
