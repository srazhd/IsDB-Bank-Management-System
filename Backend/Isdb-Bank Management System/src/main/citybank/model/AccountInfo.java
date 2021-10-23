package com.citybank.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data

public class AccountInfo {

	@GenericGenerator(name = "sampleGenerator", strategy = "enhanced-sequence", parameters = {
			@org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
			@org.hibernate.annotations.Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sampleGenerator")
	@Id
	private Long id;
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountInfo")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Deposit> deposits;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountInfo")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Withdraw> withdraws;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountInfoFrom")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Transfer> transferFroms;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountInfoTo")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Transfer> transferTos;
	private Boolean isActive;

	@PrePersist
	public void setPreInsertData() {

		this.isActive = true;
	}

}
