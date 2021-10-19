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
import com.citybank.dto.WithdrawDto;
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.WithdrawService;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.WithdrawManagement.ROOT)
public class WithdrawController {
	private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }
    
    @PostMapping(UrlConstraint.WithdrawManagement.CREATE)
    @ValidateData
    public Response create(@Valid @RequestBody WithdrawDto withdrawDto, BindingResult result) {
        return withdrawService.save(withdrawDto);
    }
    @PutMapping(UrlConstraint.WithdrawManagement.UPDATE)
    @ValidateData
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody WithdrawDto withdrawDto, BindingResult result) {
        return withdrawService.update(id, withdrawDto);
    }

    @DeleteMapping(UrlConstraint.WithdrawManagement.DELETE)
    public Response delete(@PathVariable("id") Long id) {
        return withdrawService.delete(id);
    }

    @GetMapping(UrlConstraint.WithdrawManagement.GET)
    public Response get(@PathVariable("id") Long id) {
        return withdrawService.get(id);
    }

    @GetMapping(UrlConstraint.WithdrawManagement.GET_All)
    public Response getAll() {
        return withdrawService.getAll();
    }
}
