package com.citybank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
 
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class Withdraw extends BaseModel{
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AccountInfo accountInfo;
	private Date date;
	private Double amount;
	
}
