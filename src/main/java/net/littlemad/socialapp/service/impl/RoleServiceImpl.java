package net.littlemad.socialapp.service.impl;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.Role;
import net.littlemad.socialapp.data.repository.RoleRepository;
import net.littlemad.socialapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(String id) {
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role createRole(Role role) {
        return this.roleRepository.save(role);
    }
}
