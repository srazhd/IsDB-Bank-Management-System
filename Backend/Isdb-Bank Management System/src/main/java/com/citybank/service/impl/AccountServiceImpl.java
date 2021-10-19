package com.citybank.service.impl;

 
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citybank.dto.AccountDto;
import com.citybank.dto.Response;
import com.citybank.model.Account;
import com.citybank.model.Branch;
import com.citybank.model.Customer;
import com.citybank.repository.AccountRepository;
import com.citybank.repository.BranchRepository;
import com.citybank.repository.CustomerRepository;
import com.citybank.service.AccountService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List; 

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final HikariDataSource dataSource;
    private final String root = "Account";
    
    public AccountServiceImpl(CustomerRepository customerRepository,BranchRepository branchRepository,AccountRepository accountRepository, ModelMapper modelMapper, HikariDataSource dataSource){
        this.accountRepository = accountRepository;
        this.branchRepository = branchRepository;
        this.customerRepository = customerRepository;
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper = modelMapper;
        this.dataSource = dataSource;
    }

    @Override
    public Response save(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        System.out.println(account.getBranch().getName());
        account = accountRepository.save(account);
        if(account != null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root+" created Successfully", null);
        }
        
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
    }

	@Override
//	@Transactional(readOnly = true)
	public Response update(Long id, AccountDto accountDto) {
		Account account = accountRepository.findByIdAndIsActiveTrue(id);
//		  System.out.println(account.getBranch().getName());
		
		account.setBranch(new Branch());
		account.setCustomer(new Customer());
		
        if(account != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(accountDto, account);
//            System.out.println(account.getBranch().getName());
//            System.out.println(account.getBranch().getId());
//            account.setBranch( branchRepository.findByIdAndIsActiveTrue(accountDto.getBranch().getId()));
//    		account.setCustomer(customerRepository.findByIdAndIsActiveTrue(accountDto.getCustomer().getId())); 
//    		 
            account = accountRepository.save(account);
            if(account != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" updated Successfully", null);
            }

            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response delete(Long id) {
		Account account = accountRepository.findByIdAndIsActiveTrue(id);
        if(account != null){
        	account.setIsActive(false);
        	account = accountRepository.save(account);
            if(account != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" deleted Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response get(Long id) {
		Account account =  accountRepository.findByIdAndIsActiveTrue(id);
        if(account != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            AccountDto accountDto = modelMapper.map(account, AccountDto.class);
            if(account != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", accountDto);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" not found");
    
	}

	@Override
	public Response getAll() {
		List<Account> accounts = accountRepository.findAllByIsActiveTrue();
        List<AccountDto> accountDtos = this.getAccounts(accounts);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieved Successfully", accountDtos);
   
	}

 
    private List<AccountDto> getAccounts(List<Account> accounts){
        List<AccountDto> accountDtos = new ArrayList<>();
        accounts.forEach(account -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            AccountDto accountDto = modelMapper.map(account, AccountDto.class);
            accountDtos.add(accountDto);
        });
        return accountDtos;
    }
    
    
 
}
