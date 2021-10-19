package com.citybank.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data 
public class AccountInfoDto {
	
	private Long id;
	private String name;
	

	@JsonManagedReference(value="depositInfo")  
	private List<DepositDto> deposits;
	
	
	
	@JsonManagedReference(value="withdrawInfo")  
	private List<WithdrawDto> withdraws;
	
	@JsonManagedReference(value="TransferInfoFrom")
    private List<TransferDto> transferFroms;
	
	@JsonManagedReference(value="TransferInfoTo") 
	private List<TransferDto> transferTos;
	
	
	
}
