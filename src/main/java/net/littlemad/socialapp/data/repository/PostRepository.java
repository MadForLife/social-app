package net.littlemad.socialapp.data.repository;

import net.littlemad.socialapp.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
