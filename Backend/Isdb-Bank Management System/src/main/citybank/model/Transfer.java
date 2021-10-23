package com.citybank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Transfer extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER)
	private AccountInfo accountInfoFrom;

	@ManyToOne(fetch = FetchType.EAGER)
	private AccountInfo accountInfoTo;

	@Temporal(TemporalType.DATE)
	private Date date;
	private Double amount;

}
