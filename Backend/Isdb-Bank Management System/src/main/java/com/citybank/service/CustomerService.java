package com.citybank.service;
 
import com.citybank.dto.CustomerDto;
import com.citybank.dto.Response;
 
 

public interface CustomerService {
	Response save(CustomerDto bustomerDto);
    Response update(Long id, CustomerDto bustomerDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    
}
