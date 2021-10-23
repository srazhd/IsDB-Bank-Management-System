package com.citybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citybank.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	int countByName(String name);

	Role findByName(String roleName);
}
