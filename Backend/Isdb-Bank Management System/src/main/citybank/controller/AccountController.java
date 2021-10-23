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
import com.citybank.dto.AccountDto;
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.AccountService;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.AccountManagement.ROOT)
public class AccountController {
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping(UrlConstraint.AccountManagement.CREATE)
	@ValidateData
	public Response create(@Valid @RequestBody AccountDto accountDto, BindingResult result) {
//    	System.out.println();System.out.println();System.out.println();
//    	System.out.println(accountDto.getBranch().getId());
//    	System.out.println(accountDto.getCustomer().getId());
//    	System.out.println();System.out.println();System.out.println();
		return accountService.save(accountDto);
	}

	@PutMapping(UrlConstraint.AccountManagement.UPDATE)
	@ValidateData
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody AccountDto accountDto,
			BindingResult result) {
		return accountService.update(id, accountDto);
	}

	@DeleteMapping(UrlConstraint.AccountManagement.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		return accountService.delete(id);
	}

	@GetMapping(UrlConstraint.AccountManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return accountService.get(id);
	}

	@GetMapping(UrlConstraint.AccountManagement.GET_BY_ACNO)
	public Response getbyacno(@PathVariable("id") Long id) {
		return accountService.getByAcNo(id);
	}

	@GetMapping(UrlConstraint.AccountManagement.GET_All)
	public Response getAll() {
		return accountService.getAll();
	}
}
