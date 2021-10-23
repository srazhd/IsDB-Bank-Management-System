package com.citybank.service;

import com.citybank.dto.DepositDto;
import com.citybank.dto.Response;

public interface DepositService {
	Response save(DepositDto depositDto);

	Response update(Long id, DepositDto depositDto);

	Response delete(Long id);

	Response get(Long id);

	Response getAll();

}
