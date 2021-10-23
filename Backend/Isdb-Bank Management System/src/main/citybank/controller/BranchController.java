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
import com.citybank.dto.BranchDto;
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.BranchService;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.BranchManagement.ROOT)
public class BranchController {
	private final BranchService branchService;

	public BranchController(BranchService branchService) {
		this.branchService = branchService;
	}

	@PostMapping(UrlConstraint.BranchManagement.CREATE)
	@ValidateData
	public Response create(@Valid @RequestBody BranchDto branchDto, BindingResult result) {
		return branchService.save(branchDto);
	}

	@PutMapping(UrlConstraint.BranchManagement.UPDATE)
	@ValidateData
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody BranchDto branchDto, BindingResult result) {
		return branchService.update(id, branchDto);
	}

	@DeleteMapping(UrlConstraint.BranchManagement.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		return branchService.delete(id);
	}

	@GetMapping(UrlConstraint.BranchManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return branchService.get(id);
	}

	@GetMapping(UrlConstraint.BranchManagement.GET_All)
	public Response getAll() {
		return branchService.getAll();
	}
}
