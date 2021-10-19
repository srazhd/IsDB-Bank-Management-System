package com.citybank.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.Transfer;
 
 

public interface TransferRepository extends JpaRepository<Transfer, Long> {
     
	Transfer findByIdAndIsActiveTrue(Long id);
    List<Transfer> findAllByIsActiveTrue();
}
