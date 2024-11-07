package com.rj.services;

import com.rj.models.AppUser;
import com.rj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepo;

    @Override
    public AppUser loadUserByUsername(String Username) throws UsernameNotFoundException
    {
        if (userRepo.findByUsername(Username).isPresent())
            return userRepo.findByUsername(Username).get();
        throw new UsernameNotFoundException(Username);
    }
}
