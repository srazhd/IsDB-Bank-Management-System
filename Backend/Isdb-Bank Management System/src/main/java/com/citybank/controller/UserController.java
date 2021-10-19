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
import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.service.UserService;
import com.citybank.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.UserManagement.ROOT)
public class UserController {
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping(UrlConstraint.UserManagement.CREATE)
    @ValidateData
    public Response create(@Valid @RequestBody UserDto userDto, BindingResult result) {
        return userService.save(userDto);
    }
    @PutMapping(UrlConstraint.UserManagement.UPDATE)
    @ValidateData
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto, BindingResult result) {
        return userService.update(id, userDto);
    }

    @DeleteMapping(UrlConstraint.UserManagement.DELETE)
    public Response delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

    @GetMapping(UrlConstraint.UserManagement.GET)
    public Response get(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @GetMapping(UrlConstraint.UserManagement.GET_All)
    public Response getAll() {
        return userService.getAll();
    }
}
