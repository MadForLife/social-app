package net.littlemad.socialapp.data.repository;

import net.littlemad.socialapp.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String name);
}
