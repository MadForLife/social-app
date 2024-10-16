package net.littlemad.socialapp.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "role_name", nullable = false)
    private String name;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "user_has_roles",
            joinColumns = @JoinColumn(name = "roles_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users;

}
