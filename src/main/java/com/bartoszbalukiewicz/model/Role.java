package com.bartoszbalukiewicz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by postgres on 2016-09-19.
 */
@Entity
@Table(name="role")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    public enum RoleType {
        ROLE_ADMIN
    }

    public Role(RoleType type, String title) {
        this.type = type;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_role")
    private Long id;

    @Column(name = "role_type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType type;

    @Column(name = "role_title", nullable = false)
    private String title;

    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<>();

    @Transient
    public String getTypeAsString() {
        return type.toString();
    }

}
