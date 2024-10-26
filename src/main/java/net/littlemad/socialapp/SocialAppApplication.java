package net.littlemad.socialapp;

import net.littlemad.socialapp.data.entity.Role;
import net.littlemad.socialapp.data.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import static net.littlemad.socialapp.config.Constant.ADMIN_ROLE;
import static net.littlemad.socialapp.config.Constant.USER_ROLE;

@SpringBootApplication
public class SocialAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialAppApplication.class, args);
    }

    @Component
    private static class DefaultRoles implements CommandLineRunner {

        private final RoleRepository roleRepository;

        public DefaultRoles(RoleRepository roleRepository) {
            this.roleRepository = roleRepository;
        }

        @Override
        public void run(String... args) throws Exception {

            Role admin = roleRepository.findByName(ADMIN_ROLE);
            Role user = roleRepository.findByName(USER_ROLE);

            if (admin == null || user == null) {
                roleRepository.save(new Role(USER_ROLE));
                roleRepository.save(new Role(ADMIN_ROLE));
            }
        }
    }

}
