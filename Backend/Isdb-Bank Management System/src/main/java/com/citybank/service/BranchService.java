package com.citybank.service;
 
import com.citybank.dto.BranchDto;
import com.citybank.dto.Response;
 
 

public interface BranchService {
	Response save(BranchDto branchDto);
    Response update(Long id, BranchDto branchDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    
}
