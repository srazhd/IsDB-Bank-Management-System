package com.citybank.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DepositDto extends BaseModelDto {

	@JsonBackReference(value = "depositInfo")
	private AccountInfoDto accountInfo;
	private Date date;
	private Double amount;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long acId;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private String acName;

	public Long getAcId() {
		return getAccountInfo().getId();
	}

	public void setAcId(Long acId) {
		this.acId = acId;
	}

	public String getAcName() {
		return getAccountInfo().getName();
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

}
