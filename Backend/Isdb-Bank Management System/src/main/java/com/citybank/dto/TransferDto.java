package com.citybank.dto;

import java.util.Date;
  
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
@Data

public class TransferDto extends BaseModelDto{
	@JsonBackReference(value="TransferInfoFrom") 
	private AccountInfoDto accountInfoFrom;
	
	@JsonBackReference(value="TransferInfoTo") 
	private AccountInfoDto accountInfoTo;
	
	private Date date;
	private Double amount;
}
