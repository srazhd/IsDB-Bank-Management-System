package com.citybank.service;

import com.citybank.dto.WithdrawDto;
import com.citybank.dto.Response;

public interface WithdrawService {
	Response save(WithdrawDto withdrawDto);

	Response update(Long id, WithdrawDto withdrawDto);

	Response delete(Long id);

	Response get(Long id);

	Response getAll();

}
