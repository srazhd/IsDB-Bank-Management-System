package com.citybank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citybank.model.AccountInfo;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {

	AccountInfo findByIdAndIsActiveTrue(Long id);

	List<AccountInfo> findAllByIsActiveTrue();
}
