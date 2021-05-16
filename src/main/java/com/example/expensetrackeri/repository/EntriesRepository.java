package com.example.expensetrackeri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.expensetrackeri.model.Entries;

public interface EntriesRepository extends JpaRepository<Entries, Long> {

	List<Entries> findByUserId(Long userid);
}
