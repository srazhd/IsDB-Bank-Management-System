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
import com.citybank.dto.DepositDto;
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.DepositService;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.DepositManagement.ROOT)
public class DepositController {
	private final DepositService depositService;

	public DepositController(DepositService depositService) {
		this.depositService = depositService;
	}

	@PostMapping(UrlConstraint.DepositManagement.CREATE)
	@ValidateData
	public Response create(@Valid @RequestBody DepositDto depositDto, BindingResult result) {
		return depositService.save(depositDto);
	}

	@PutMapping(UrlConstraint.DepositManagement.UPDATE)
	@ValidateData
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody DepositDto depositDto,
			BindingResult result) {
		return depositService.update(id, depositDto);
	}

	@DeleteMapping(UrlConstraint.DepositManagement.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		return depositService.delete(id);
	}

	@GetMapping(UrlConstraint.DepositManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return depositService.get(id);
	}

	@GetMapping(UrlConstraint.DepositManagement.GET_All)
	public Response getAll() {
		return depositService.getAll();
	}
}
