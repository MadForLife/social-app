package net.littlemad.socialapp.service.impl;

import net.littlemad.socialapp.data.entity.Role;
import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.data.repository.RoleRepository;
import net.littlemad.socialapp.data.repository.UserRepository;
import net.littlemad.socialapp.dto.CreateUserDTO;
import net.littlemad.socialapp.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.littlemad.socialapp.config.Constant.USER_ROLE;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final Role defaultRole;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultRole = roleRepository.findByName(USER_ROLE);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();

        user.setUsername(createUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setDisplayName(createUserDTO.getDisplayName());
        user.setEnabled(true);

        user.setRoles(Set.of(defaultRole));

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
}
