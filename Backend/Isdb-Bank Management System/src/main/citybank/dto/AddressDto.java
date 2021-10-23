package com.citybank.dto;

import lombok.Data;

@Data
public class AddressDto extends BaseModelDto {
	private String streetName;
	private String city;
	private String country;
	private String zipCode;
}
