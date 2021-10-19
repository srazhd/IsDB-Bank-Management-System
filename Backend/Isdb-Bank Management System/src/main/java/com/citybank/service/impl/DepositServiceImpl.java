package com.citybank.service.impl;

 
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.citybank.dto.AccountDto;
import com.citybank.dto.DepositDto;
import com.citybank.dto.Response;
import com.citybank.model.Account;
import com.citybank.model.Deposit;
import com.citybank.repository.AccountRepository;
import com.citybank.repository.DepositRepository;
import com.citybank.service.DepositService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List; 

@Service("depositService")
public class DepositServiceImpl implements DepositService {
    
	private final AccountRepository accountRepository;
	private final DepositRepository depositRepository;
    private final ModelMapper modelMapper;
    private final HikariDataSource dataSource;
    private final String root = "Deposit";
    
    public DepositServiceImpl(AccountRepository accountRepository,DepositRepository depositRepository, ModelMapper modelMapper, HikariDataSource dataSource){
        this.accountRepository =accountRepository;
    	this.depositRepository = depositRepository;
        
//    	PropertyMap <DepositDto, Deposit> orderMap = new PropertyMap <DepositDto, Deposit>() {
//    		  protected void configure() {
//    		    map().getAccount().setId(source.getAccount().getId());
//	    	  }
//    	}; 
//    	modelMapper.addMappings(orderMap);
//    	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        this.modelMapper = modelMapper;
        this.dataSource = dataSource;
    }

    @Override
    public Response save(DepositDto depositDto) {
//    	modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        modelMapper.getConfiguration()
//        .setFieldMatchingEnabled(true)
//        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
//        
//        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        	
        Deposit deposit = modelMapper.map(depositDto, Deposit.class);
       
        deposit = depositRepository.save(deposit);
        if(deposit != null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root+" created Successfully", null);
        }
        
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
    }

	@Override
	public Response update(Long id, DepositDto depositDto) {
		Deposit deposit = depositRepository.findByIdAndIsActiveTrue(id);
		
        if(deposit != null){
//        	PropertyMap <DepositDto, Deposit> orderMap2 = new PropertyMap <DepositDto, Deposit>() {
//      		  protected void configure() {
//      		    map().getAccount().setId(source.getAccount().getId());
//  	    	  }
//      	}; 
//      	modelMapper.addMappings(orderMap2);
//      	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
      	
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(depositDto, deposit);
             
            deposit = depositRepository.save(deposit);
            if(deposit != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" updated Successfully", null);
            }

            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response delete(Long id) {
		Deposit deposit = depositRepository.findByIdAndIsActiveTrue(id);
        if(deposit != null){
        	deposit.setIsActive(false);
        	deposit = depositRepository.save(deposit);
            if(deposit != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" deleted Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response get(Long id) {
		Deposit deposit =  depositRepository.findByIdAndIsActiveTrue(id);
        if(deposit != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            DepositDto depositDto = modelMapper.map(deposit, DepositDto.class);
            if(deposit != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", depositDto);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response getAll() {
		List<Deposit> deposits = depositRepository.findAllByIsActiveTrue();
        List<DepositDto> depositDtos = this.getDeposits(deposits);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", depositDtos);
   
	}

 
    private List<DepositDto> getDeposits(List<Deposit> deposits){
        List<DepositDto> depositDtos = new ArrayList<>();
        deposits.forEach(deposit -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            DepositDto depositDto = modelMapper.map(deposit, DepositDto.class);
            depositDtos.add(depositDto);
        });
        return depositDtos;
    }
    
    
 
}
