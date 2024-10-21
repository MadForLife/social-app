package net.littlemad.socialapp.service;

import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.dto.CreateUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    // FIXME Return CreateUserDTO
    User createUser(CreateUserDTO createUserDTO);
    List<User> getUsers();
}
