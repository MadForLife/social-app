package net.littlemad.socialapp.data.repository;

import net.littlemad.socialapp.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u WHERE u.username = :username and u.enabled = true ")
    User findByUsername(@Param("username") String username);
}
