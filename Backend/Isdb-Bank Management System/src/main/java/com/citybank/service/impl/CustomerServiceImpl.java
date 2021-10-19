package com.citybank.service.impl;

 
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.citybank.dto.CustomerDto;
import com.citybank.dto.Response;
import com.citybank.model.Customer; 
import com.citybank.repository.CustomerRepository;
import com.citybank.repository.CustomerRepository;
import com.citybank.service.AccountService;
import com.citybank.service.CustomerService;
import com.citybank.service.CustomerService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List; 

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final HikariDataSource dataSource;
    private final String root = "Customer";
    
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, HikariDataSource dataSource){
        this.customerRepository = customerRepository;
        
        this.modelMapper = modelMapper;
        this.dataSource = dataSource;
    }

    @Override
    public Response save(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer = customerRepository.save(customer);
        if(customer != null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root+" created Successfully", null);
        }
        
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
    }

	@Override
	public Response update(Long id, CustomerDto customerDto) {
		Customer customer = customerRepository.findByIdAndIsActiveTrue(id);
		
        if(customer != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(customerDto, customer);
             
            customer = customerRepository.save(customer);
            if(customer != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" updated Successfully", null);
            }

            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response delete(Long id) {
		Customer customer = customerRepository.findByIdAndIsActiveTrue(id);
        if(customer != null){
        	customer.setIsActive(false);
        	customer = customerRepository.save(customer);
            if(customer != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" deleted Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response get(Long id) {
		Customer customer =  customerRepository.findByIdAndIsActiveTrue(id);
        if(customer != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
            if(customer != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", customerDto);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response getAll() {
		List<Customer> customers = customerRepository.findAllByIsActiveTrue();
        List<CustomerDto> customerDtos = this.getCustomers(customers);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", customerDtos);
   
	}

 
    private List<CustomerDto> getCustomers(List<Customer> customers){
        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.forEach(customer -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
            customerDtos.add(customerDto);
        });
        return customerDtos;
    }
    
    
 
}
