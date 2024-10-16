package net.littlemad.socialapp.web.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.dto.CreateUserDTO;
import net.littlemad.socialapp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        // FIXME Don't throw error return a response saying username already exists
        User existingUser = userService.findByUsername(createUserDTO.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists");
        }

        return userService.createUser(createUserDTO);
    }
}
