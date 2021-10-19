package com.citybank.service;
 
import com.citybank.dto.AccountInfoDto;
import com.citybank.dto.Response;
 
 

public interface AccountInfoService {
	Response save(AccountInfoDto accountInfoDto);
    Response update(Long id, AccountInfoDto accountInfoDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    
}
