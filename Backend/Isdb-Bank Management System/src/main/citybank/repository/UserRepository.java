package com.citybank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameAndIsActiveTrue(String username);

	User findByIdAndIsActiveTrue(Long id);

	List<User> findAllByIsActiveTrue();
}
