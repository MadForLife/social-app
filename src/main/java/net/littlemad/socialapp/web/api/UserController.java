package net.littlemad.socialapp.web.api;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
