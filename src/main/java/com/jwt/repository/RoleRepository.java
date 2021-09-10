package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.domain.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	
	Role findByName(String name);

}
