package com.rj.repository;

import com.rj.models.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer>
{
    Optional<AppUser> findByUsername(String username);
}
