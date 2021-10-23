package com.citybank.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BranchDto extends BaseModelDto {
	private String name;
	private String email;
	private String phone;
	private AddressDto address;

	@JsonManagedReference(value = "branchInfo1")
	private List<AccountDto> accounts;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Integer numberOfAccount = 0;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double branchBalance = 0.0;

	public Integer getNumberOfAccount() {
		accounts.forEach((n) -> this.numberOfAccount += 1);
		return numberOfAccount;
	}

	public void setNumberOfAccount(Integer numberOfAccount) {
		this.numberOfAccount = numberOfAccount;
	}

	public Double getBranchBalance() {
		accounts.forEach((n) -> this.branchBalance += n.getAccountInfo().getTotalBalance());
		return branchBalance;
	}

	public void setBranchBalance(Double branchBalance) {
		this.branchBalance = branchBalance;
	}

}
