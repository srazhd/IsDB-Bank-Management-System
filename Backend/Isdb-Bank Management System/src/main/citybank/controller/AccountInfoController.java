package com.citybank.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citybank.annotations.ApiController;
import com.citybank.annotations.ValidateData;
import com.citybank.dto.AccountInfoDto;
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.AccountInfoService;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.AccountInfoManagement.ROOT)
public class AccountInfoController {
	private final AccountInfoService accountInfoService;

	public AccountInfoController(AccountInfoService accountInfoService) {
		this.accountInfoService = accountInfoService;
	}

	@PostMapping(UrlConstraint.AccountInfoManagement.CREATE)
	@ValidateData
	public Response create(@Valid @RequestBody AccountInfoDto accountInfoDto, BindingResult result) {

		return accountInfoService.save(accountInfoDto);
	}

	@PutMapping(UrlConstraint.AccountInfoManagement.UPDATE)
	@ValidateData
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody AccountInfoDto accountInfoDto,
			BindingResult result) {
		return accountInfoService.update(id, accountInfoDto);
	}

	@DeleteMapping(UrlConstraint.AccountInfoManagement.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		return accountInfoService.delete(id);
	}

	@GetMapping(UrlConstraint.AccountInfoManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return accountInfoService.get(id);
	}

	@GetMapping(UrlConstraint.AccountInfoManagement.GET_All)
	public Response getAll() {
		return accountInfoService.getAll();
	}
}
