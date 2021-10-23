package com.citybank.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AccountDto extends BaseModelDto {

	private AccountInfoDto accountInfo;

	@JsonBackReference(value = "branchInfo1")
	private BranchDto branch;

	@JsonBackReference(value = "custInfo")
	private CustomerDto customer;

	private AddressDto address;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long custId = 0L;

	public Long getCustId() {
		return getCustomer().getId();
	}

	public void setCustId(Long customerId) {
		this.custId = customerId;
	}

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long branId = 0L;

	public Long getBranId() {
		return getBranch().getId();
	}

	public void setBranchId(Long branId) {
		this.branId = branId;
	}

}
