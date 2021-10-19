package com.citybank.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
public class BranchDto extends BaseModelDto{
	private String name;
	private String email;
	private String phone;
	private AddressDto address;
	
	@JsonManagedReference(value="branchInfo1")  
	private List<AccountDto> accounts;
	
}
