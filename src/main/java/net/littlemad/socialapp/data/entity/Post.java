package net.littlemad.socialapp.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Column(name = "post_media_url", nullable = false)
    private String mediaUrl;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User author;

    @ManyToMany(mappedBy = "posts")
    private Set<Board> boards;

}
