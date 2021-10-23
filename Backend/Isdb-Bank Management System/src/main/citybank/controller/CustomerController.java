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
import com.citybank.dto.CustomerDto;
import com.citybank.dto.Response;
import com.citybank.service.CustomerService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.CustomerManagement.ROOT)
public class CustomerController {
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping(UrlConstraint.CustomerManagement.CREATE)
	@ValidateData
	public Response create(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
		return customerService.save(customerDto);
	}

	@PutMapping(UrlConstraint.CustomerManagement.UPDATE)
	@ValidateData
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto customerDto,
			BindingResult result) {
		return customerService.update(id, customerDto);
	}

	@DeleteMapping(UrlConstraint.CustomerManagement.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		return customerService.delete(id);
	}

	@GetMapping(UrlConstraint.CustomerManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return customerService.get(id);
	}

	@GetMapping(UrlConstraint.CustomerManagement.GET_All)
	public Response getAll() {
		return customerService.getAll();
	}
}
