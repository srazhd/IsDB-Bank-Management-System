package com.citybank.service;

import com.citybank.dto.TransferDto;
import com.citybank.dto.Response;

public interface TransferService {
	Response save(TransferDto transferDto);

	Response update(Long id, TransferDto transferDto);

	Response delete(Long id);

	Response get(Long id);

	Response getAll();

}
