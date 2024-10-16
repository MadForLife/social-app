package net.littlemad.socialapp.web.api;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.Role;
import net.littlemad.socialapp.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
}
