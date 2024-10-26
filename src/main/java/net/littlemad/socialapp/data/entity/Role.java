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

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Column(name = "role_name", nullable = false)
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
