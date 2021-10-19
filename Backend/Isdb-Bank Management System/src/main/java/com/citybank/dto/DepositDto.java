package com.citybank.dto;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data   
public class DepositDto extends BaseModelDto{
    
	@JsonBackReference(value="depositInfo") 
	private AccountInfoDto accountInfo;
	private Date date;
	private Double amount;
	


	
		
}
