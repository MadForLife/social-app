package net.littlemad.socialapp.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "user_username", nullable = false, unique = true)
    private String username;

    @Column(name = "user_display_name", nullable = false)
    private String displayName;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_account_non_expired", columnDefinition = "TINYINT(1) NOT NULL")
    private boolean accountNonExpired;

    @Column(name = "user_account_non_locked", columnDefinition = "TINYINT(1) NOT NULL")
    private boolean accountNonLocked;

    @Column(name = "user_credentials_non_expired", columnDefinition = "TINYINT(1) NOT NULL")
    private boolean credentialsNonExpired;

    @Column(name = "user_enabled", columnDefinition = "TINYINT(1) NOT NULL")
    private boolean enabled;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts;

    @OneToMany(mappedBy = "author")
    private Set<Board> boards;

    @JsonManagedReference
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Role> roles;

}
