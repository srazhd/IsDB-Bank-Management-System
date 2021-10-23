package com.citybank.dto;

import java.util.List;

import com.citybank.model.UserAddress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private String username;
	private String password;
	private AddressDto address;
	private HomeDto home;
	private List<RoleDto> roles;
}
