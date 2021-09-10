package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
		User findByUsername(String username);
}
