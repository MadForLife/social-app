package net.littlemad.socialapp.service;

import net.littlemad.socialapp.data.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();
    Role findById(String id);
    Role createRole(Role role);
}
