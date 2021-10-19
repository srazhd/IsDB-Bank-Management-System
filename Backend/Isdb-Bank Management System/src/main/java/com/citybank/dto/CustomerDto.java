package com.citybank.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Data
public class CustomerDto extends BaseModelDto{
	private String name;
	private String fatherName;
	private String motherName;
	private String spousName;
	private String email;
	private String phone;
	private Long nid;
	private Date dob;
	 
	private AddressDto address;
	
	@JsonManagedReference(value="custInfo")  
	private List<AccountDto> account;
}
