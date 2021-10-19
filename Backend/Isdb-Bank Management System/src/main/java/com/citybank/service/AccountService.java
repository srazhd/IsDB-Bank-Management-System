package com.citybank.service;
 
import com.citybank.dto.AccountDto;
import com.citybank.dto.Response;
 
 

public interface AccountService {
	Response save(AccountDto accountDto);
    Response update(Long id, AccountDto accountDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    
}
