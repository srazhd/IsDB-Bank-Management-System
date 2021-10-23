package com.citybank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	@NotBlank(message = "Username mandatory")
	private String username;
	@NotBlank(message = "Password mandatory")
	private String password;
}
