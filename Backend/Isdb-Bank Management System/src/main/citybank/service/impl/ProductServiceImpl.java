package com.citybank.service.impl;

import com.citybank.dto.ProductDto;
import com.citybank.dto.Response;
import com.citybank.model.Product;
import com.citybank.repository.ProductRepository;
import com.citybank.service.ProductService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	private final HikariDataSource dataSource;
	private final MailService mailService;
	private final String root = "Product";
	@Value("classpath:reports/product.jasper")
	private Resource reportResource;

	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, HikariDataSource dataSource,
			MailService mailService) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.dataSource = dataSource;
		this.mailService = mailService;
	}

	@Override
	public Response save(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product = productRepository.save(product);
		if (product != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created Successfully", null);
		}
		String to[] = { "habib@abc.com" };
		mailService.sendNonHtmlMail(to, "Test mail", "Hello Habib");
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
	}

	@Override
	public Response update(Long id, ProductDto productDto) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(productDto, product);
			product = productRepository.save(product);
			if (product != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
			}

			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response delete(Long id) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			product.setIsActive(false);
			product = productRepository.save(product);
			if (product != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response get(Long id) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			if (product != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", productDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response getAll() {
		List<Product> products = productRepository.findAllByIsActiveTrue();
		List<ProductDto> productDtos = this.getProducts(products);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", productDtos);
	}

	@Override
	public HttpEntity<byte[]> getPdfResponse(HttpServletResponse response) {
		Map<String, Object> reportParams = new HashMap<>();
		try {
			JasperPrint print = JasperFillManager.fillReport(reportResource.getFile().getPath(), reportParams,
					dataSource.getConnection());
			byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			response.setHeader("Content-Description", "attachment; filename=abc.pdf");
			return new HttpEntity<>(pdfBytes, headers);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	private List<ProductDto> getProducts(List<Product> products) {
		List<ProductDto> productDtos = new ArrayList<>();
		products.forEach(product -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			productDtos.add(productDto);
		});
		return productDtos;
	}

}
