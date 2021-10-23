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
import com.citybank.dto.TransferDto;
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.TransferService;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.TransferManagement.ROOT)
public class TransferController {
	private final TransferService transferService;

	public TransferController(TransferService transferService) {
		this.transferService = transferService;
	}

	@PostMapping(UrlConstraint.TransferManagement.CREATE)
	@ValidateData
	public Response create(@Valid @RequestBody TransferDto transferDto, BindingResult result) {
		return transferService.save(transferDto);
	}

	@PutMapping(UrlConstraint.TransferManagement.UPDATE)
	@ValidateData
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody TransferDto transferDto,
			BindingResult result) {
		return transferService.update(id, transferDto);
	}

	@DeleteMapping(UrlConstraint.TransferManagement.DELETE)
	public Response delete(@PathVariable("id") Long id) {
		return transferService.delete(id);
	}

	@GetMapping(UrlConstraint.TransferManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return transferService.get(id);
	}

	@GetMapping(UrlConstraint.TransferManagement.GET_All)
	public Response getAll() {
		return transferService.getAll();
	}
}
