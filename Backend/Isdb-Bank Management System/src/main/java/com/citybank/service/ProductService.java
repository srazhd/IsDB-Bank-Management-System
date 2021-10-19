package com.citybank.service;

import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import org.springframework.http.HttpEntity;

import javax.servlet.http.HttpServletResponse;

public interface ProductService {
    Response save(ProductDto productDto);
    Response update(Long id, ProductDto productDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    HttpEntity<byte[]> getPdfResponse(HttpServletResponse response);
}
