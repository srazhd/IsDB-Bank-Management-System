package com.citybank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.Account;
import com.citybank.model.AccountInfo;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByIdAndIsActiveTrue(Long id);

	Account findByAccountInfoAndIsActiveTrue(AccountInfo accountInfo);

	List<Account> findAllByIsActiveTrue();
}
