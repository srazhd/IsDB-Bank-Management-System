package com.citybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citybank.model.ActionLog;


public interface ActionRepository extends JpaRepository<ActionLog, Long> {
	 
}
