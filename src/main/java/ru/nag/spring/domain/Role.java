package ru.nag.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", length = 20, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(String s) {
    }
}
