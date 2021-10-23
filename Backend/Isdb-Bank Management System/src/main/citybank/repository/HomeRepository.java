package com.citybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.Home;

public interface HomeRepository extends JpaRepository<Home, Long> {
	int countByName(String name);

	Home findByName(String homeName);
}
