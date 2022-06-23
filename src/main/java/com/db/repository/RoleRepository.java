package com.db.repository;

import com.db.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getFirstByAuthority(String authority);
}