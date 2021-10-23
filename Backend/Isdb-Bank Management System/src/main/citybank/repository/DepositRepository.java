package com.citybank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

	Deposit findByIdAndIsActiveTrue(Long id);

	List<Deposit> findAllByIsActiveTrue();
}
