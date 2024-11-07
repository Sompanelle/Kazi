package com.rj.services;

import com.rj.dto.UserDTO;
import com.rj.models.AppUser;
import com.rj.models.Role;
import com.rj.repository.RoleRepository;
import com.rj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService
{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;


    public UserDTO loginUser(String Username, String Password)
    {
        try {
            Authentication authReq = authManager.authenticate( UsernamePasswordAuthenticationToken.unauthenticated(Username, Password) );

            Authentication authResponse = authManager.authenticate(authReq);

            return new UserDTO(userRepo.findByUsername(Username).get());

        } catch (AuthenticationException e)
        {
            return new UserDTO(null);
        }
    }

    public UserDTO registerUser(String Username, String Password)
    {
        if (userRepo.findByUsername(Username) == null)
            return new UserDTO(null);

        String encodedPassword = passwordEncoder.encode(Password);
        Role userRole = roleRepo.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        return new UserDTO(userRepo.save(new AppUser(Username, encodedPassword, roles)));
    }

}
