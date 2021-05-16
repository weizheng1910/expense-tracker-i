package com.example.expensetrackeri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetrackeri.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
