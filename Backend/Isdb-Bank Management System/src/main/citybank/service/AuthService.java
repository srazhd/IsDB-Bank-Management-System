package com.citybank.service;

import com.citybank.dto.LoginDto;
import com.citybank.dto.Response;

public interface AuthService {
	Response login(LoginDto loginDto);
}
