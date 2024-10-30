package net.littlemad.socialapp.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "boards")
public class Board extends BaseEntity {

    @Column(name = "board_name", nullable = false)
    private String name;

    @Column(name = "board_is_private", columnDefinition = "TINYINT(1) NOT NULL")
    private boolean isPrivate;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(name = "board_has_posts",
            joinColumns = @JoinColumn(name = "boards_id"),
            inverseJoinColumns = @JoinColumn(name = "posts_id"))
    private Set<Post> posts;

    public Board() {
    }

    public Board(String name, boolean isPrivate) {
        this.name = name;
        this.isPrivate = isPrivate;
    }
}
