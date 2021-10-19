package com.citybank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
@Entity
@Data
public class Branch extends BaseModel{
	private String name;
	private String email;
	private String phone;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "branch")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Account> accounts;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
}
