package com.citybank.controller;

import com.citybank.annotations.ApiController;
import com.citybank.dto.LoginDto;
import com.citybank.dto.Response;
import com.citybank.service.AuthService;
import com.citybank.service.ProductService;
import com.citybank.util.UrlConstraint;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiController
@RequestMapping(UrlConstraint.AuthManagement.ROOT)
public class AuthController {

    private final AuthService authService;
    private final ProductService productService;
    public AuthController(AuthService authService, ProductService productService){
        this.authService = authService;
        this.productService = productService;
    }
//    @CrossOrigin(origins = "http://localhost:4200/login")
    @PostMapping(UrlConstraint.AuthManagement.LOGIN)
    public Response login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getUsername());
    	return authService.login(loginDto);
    }

    @GetMapping(UrlConstraint.AuthManagement.DOWNLOAD)
    public HttpEntity<byte[]> getPdf(HttpServletResponse response){
        return productService.getPdfResponse(response);
    }
}
