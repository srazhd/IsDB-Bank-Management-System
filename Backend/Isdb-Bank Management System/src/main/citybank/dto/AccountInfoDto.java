package com.citybank.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AccountInfoDto {

	private Long id;
	private String name;

	@JsonManagedReference(value = "depositInfo")
	private List<DepositDto> deposits;

	@JsonManagedReference(value = "withdrawInfo")
	private List<WithdrawDto> withdraws;

	@JsonManagedReference(value = "TransferInfoFrom")
	private List<TransferDto> transferFroms;

	@JsonManagedReference(value = "TransferInfoTo")
	private List<TransferDto> transferTos;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double totalDeposit = 0.0;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double totalWithdraw = 0.0;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double totaltransferFrom = 0.0;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double totaltransferTo = 0.0;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Double totalBalance = 0.0;

	private Long statementID = 0L;
	private Double totalStatementBalance = 0.0;

	public Double getTotalDeposit() {
		deposits.forEach((n) -> {
			this.totalDeposit += n.getAmount();
			this.statementDataDtos.add(new StatementDataDto(n.getDate(), "D", n.getAmount()));

		});
		return totalDeposit;
	}

	public void setTotalDeposit(Double totalDeposit) {
		this.totalDeposit = totalDeposit;
	}

	public Double getTotalWithdraw() {
		withdraws.forEach((n) -> {
			this.totalWithdraw += n.getAmount();
			this.statementDataDtos.add(new StatementDataDto(n.getDate(), "W", n.getAmount()));

		});
		return totalWithdraw;
	}

	public void setTotalWithdraw(Double totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

	public Double getTotaltransferFrom() {
		transferFroms.forEach((n) -> {
			this.totaltransferFrom += n.getAmount();
			this.statementDataDtos.add(new StatementDataDto(n.getDate(), "TF", n.getAmount()));

		});
		return totaltransferFrom;
	}

	public void setTotaltransferFrom(Double totaltransferFrom) {
		this.totaltransferFrom = totaltransferFrom;
	}

	public Double getTotaltransferTo() {
		transferTos.forEach((n) -> {
			this.totaltransferTo += n.getAmount();
			this.statementDataDtos.add(new StatementDataDto(n.getDate(), "TT", n.getAmount()));

		});
		return totaltransferTo;
	}

	public void setTotaltransferTo(Double totaltransferTo) {
		this.totaltransferTo = totaltransferTo;
	}

	public Double getTotalBalance() {
		totalBalance = totalDeposit - totalWithdraw - totaltransferFrom + totaltransferTo;
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private List<StatementDataDto> statementDataDtos = new ArrayList<>();

	public List<StatementDataDto> getStatementDataDtos() {

		Collections.sort(statementDataDtos);

		statementDataDtos.forEach((n) -> {

			n.setId(statementID + 1);
			if (n.getType().equals("D")) {
				totalStatementBalance += n.getAmount();
			} else if (n.getType().equals("W")) {
				totalStatementBalance -= n.getAmount();
			} else if (n.getType().equals("TF")) {
				totalStatementBalance -= n.getAmount();
			} else if (n.getType().equals("TT")) {
				totalStatementBalance += n.getAmount();
			}

			n.setTotal(totalStatementBalance);

		});

		return statementDataDtos;
	}

	public void setStatementDataDtos(List<StatementDataDto> statementDataDtos) {
		this.statementDataDtos = statementDataDtos;
	}

}
