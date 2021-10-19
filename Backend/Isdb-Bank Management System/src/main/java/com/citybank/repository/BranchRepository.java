package com.citybank.repository;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citybank.model.Branch;



public interface BranchRepository extends JpaRepository<Branch, Long> {
	Branch findByIdAndIsActiveTrue(Long id);
	List<Branch> findAllByIsActiveTrue();
}
