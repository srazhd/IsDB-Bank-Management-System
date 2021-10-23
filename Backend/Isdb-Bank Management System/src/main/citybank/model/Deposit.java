package com.citybank.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Deposit extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER)
	private AccountInfo accountInfo;
	@Temporal(TemporalType.DATE)
	private Date date;
	private Double amount;

}
