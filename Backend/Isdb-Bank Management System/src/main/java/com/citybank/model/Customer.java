package com.citybank.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity 
@Data
public class Customer extends BaseModel{
	
	private String name;
	private String fatherName;
	private String motherName;
	private String spousName;
	private String email;
	private String phone;
	private Long nid;
	@Temporal(TemporalType.DATE)
	private Date dob;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	 
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Account> account;

}
