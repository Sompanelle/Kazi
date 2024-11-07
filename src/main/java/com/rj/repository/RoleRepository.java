package com.rj.repository;

import com.rj.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>
{
    Optional<Role> findByAuthority(String Authority);
}
