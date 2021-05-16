package com.example.expensetrackeri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetrackeri.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String Name);
}
