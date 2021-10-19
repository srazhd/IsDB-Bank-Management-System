package com.citybank.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.Account;
 
 

public interface AccountRepository extends JpaRepository<Account, Long> {
     
	Account findByIdAndIsActiveTrue(Long id);
    List<Account> findAllByIsActiveTrue();
}
