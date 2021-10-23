package com.citybank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

	Withdraw findByIdAndIsActiveTrue(Long id);

	List<Withdraw> findAllByIsActiveTrue();
}
