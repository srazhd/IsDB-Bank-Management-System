package com.citybank.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;

import com.citybank.model.Address;
 

public interface AddressRepository extends JpaRepository<Address, Long> {
     
}
