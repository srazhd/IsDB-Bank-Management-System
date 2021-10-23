package com.citybank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class TransferDto extends BaseModelDto {
	@JsonBackReference(value = "TransferInfoFrom")
	private AccountInfoDto accountInfoFrom;

	@JsonBackReference(value = "TransferInfoTo")
	private AccountInfoDto accountInfoTo;

	private Date date;
	private Double amount;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long acIdFrom;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private String acNameFrom;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Long acIdTo;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private String acNameTo;

	public Long getAcIdFrom() {
		return getAccountInfoFrom().getId();
	}

	public void setAcIdFrom(Long acIdFrom) {
		this.acIdFrom = acIdFrom;
	}

	public Long getAcIdTo() {
		return getAccountInfoTo().getId();
	}

	public void setAcIdTo(Long acIdTo) {
		this.acIdTo = acIdTo;
	}

	public String getAcNameFrom() {
		return getAccountInfoFrom().getName();
	}

	public void setAcNameFrom(String acName) {
		this.acNameFrom = acName;
	}

	public String getAcNameTo() {
		return getAccountInfoTo().getName();
	}

	public void setAcName(String acName) {
		this.acNameTo = acName;
	}

}
