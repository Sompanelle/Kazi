package com.rj;

import com.rj.models.AppUser;
import com.rj.models.Role;
import com.rj.repository.RoleRepository;
import com.rj.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class KaziApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaziApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository RoleRepo, UserRepository UserRepo, PasswordEncoder PasswordEncoder)
    {
        return args -> {
            if( RoleRepo.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = new Role("ADMIN");
            RoleRepo.save(new Role("USER"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            AppUser Admin = new AppUser("admin", PasswordEncoder.encode("Pass"), adminRoles);
            UserRepo.save(Admin);
        };
    }
}
