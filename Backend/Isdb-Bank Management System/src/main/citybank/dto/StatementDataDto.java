package com.citybank.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StatementDataDto implements Comparable<StatementDataDto> {
	private Long id;
	private Date date;
	private String type;
	private Double amount;
	private Double total;

	public StatementDataDto(Date date, String type, Double amount) {
		super();

		this.date = date;
		this.type = type;
		this.amount = amount;

	}

	@Override
	public int compareTo(StatementDataDto o) {
		return getDate().compareTo(o.getDate());
	}
}
