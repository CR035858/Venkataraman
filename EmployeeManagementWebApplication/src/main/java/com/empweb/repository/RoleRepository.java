package com.empweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empweb.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
