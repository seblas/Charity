package pl.coderslab.charity.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Transient
    private String password2;

    private boolean enabled;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities")
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany
    private Set<Donation> donations = new HashSet<>();

}
