package com.citybank.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity 
@Data

public class Account extends BaseModel{
	
	@OneToOne(cascade = CascadeType.ALL)
	private AccountInfo accountInfo;
	
	@ManyToOne(fetch = FetchType.EAGER)
//	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Branch branch;
	
	@ManyToOne( fetch = FetchType.EAGER)
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
}
