package com.citybank.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.citybank.model.Customer;
 

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     
	Customer findByIdAndIsActiveTrue(Long id);
    List<Customer> findAllByIsActiveTrue();
}
