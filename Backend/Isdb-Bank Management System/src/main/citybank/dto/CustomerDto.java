package com.citybank.dto;

import java.util.Date;
import java.util.List;
 

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerDto extends BaseModelDto {
	private String name;
	private String fatherName;
	private String motherName;
	private String spousName;
	private String email;
	private String phone;
	private Long nid;
	private Date dob;

	private AddressDto address;

	@JsonManagedReference(value = "custInfo")
	private List<AccountDto> accounts;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Integer numberOfAccount = 0;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double customerBalance = 0.0;

	public Integer getNumberOfAccount() {
		accounts.forEach((n) -> this.numberOfAccount += 1);
		return numberOfAccount;
	}

	public void setNumberOfAccount(Integer numberOfAccount) {
		this.numberOfAccount = numberOfAccount;
	}

	public Double getCustomerBalance() {
		accounts.forEach((n) -> this.customerBalance += n.getAccountInfo().getTotalBalance());
		return customerBalance;
	}

	public void setCustomerBalance(Double customerBalance) {
		this.customerBalance = customerBalance;
	}
}
