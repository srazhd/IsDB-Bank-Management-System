package com.citybank.service.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citybank.dto.AccountInfoDto;
import com.citybank.dto.Response;
import com.citybank.model.AccountInfo;
import com.citybank.model.Branch;
import com.citybank.model.Customer;
import com.citybank.repository.AccountInfoRepository;
import com.citybank.repository.BranchRepository;
import com.citybank.repository.CustomerRepository;
import com.citybank.service.AccountInfoService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List;

@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {
	private final AccountInfoRepository accountInfoRepository;
	private final BranchRepository branchRepository;
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	private final HikariDataSource dataSource;
	private final String root = "AccountInfo";

	public AccountInfoServiceImpl(CustomerRepository customerRepository, BranchRepository branchRepository,
			AccountInfoRepository accountInfoRepository, ModelMapper modelMapper, HikariDataSource dataSource) {
		this.accountInfoRepository = accountInfoRepository;
		this.branchRepository = branchRepository;
		this.customerRepository = customerRepository;
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		this.modelMapper = modelMapper;
		this.dataSource = dataSource;
	}

	@Override
	public Response save(AccountInfoDto accountInfoDto) {
		AccountInfo accountInfo = modelMapper.map(accountInfoDto, AccountInfo.class);

		accountInfo = accountInfoRepository.save(accountInfo);
		if (accountInfo != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created Successfully", null);
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
	}

	@Override
//	@Transactional(readOnly = true)
	public Response update(Long id, AccountInfoDto accountInfoDto) {
		AccountInfo accountInfo = accountInfoRepository.findByIdAndIsActiveTrue(id);
//		  System.out.println(accountInfo.getBranch().getName());

		if (accountInfo != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(accountInfoDto, accountInfo);
//            System.out.println(accountInfo.getBranch().getName());
//            System.out.println(accountInfo.getBranch().getId());
//            accountInfo.setBranch( branchRepository.findByIdAndIsActiveTrue(accountInfoDto.getBranch().getId()));
//    		accountInfo.setCustomer(customerRepository.findByIdAndIsActiveTrue(accountInfoDto.getCustomer().getId())); 
//    		 
			accountInfo = accountInfoRepository.save(accountInfo);
			if (accountInfo != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
			}

			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response delete(Long id) {
		AccountInfo accountInfo = accountInfoRepository.findByIdAndIsActiveTrue(id);
		if (accountInfo != null) {
			accountInfo.setIsActive(false);
			accountInfo = accountInfoRepository.save(accountInfo);
			if (accountInfo != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response get(Long id) {
		AccountInfo accountInfo = accountInfoRepository.findByIdAndIsActiveTrue(id);
		if (accountInfo != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			AccountInfoDto accountInfoDto = modelMapper.map(accountInfo, AccountInfoDto.class);
			if (accountInfo != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully",
						accountInfoDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response getAll() {
		List<AccountInfo> accountInfos = accountInfoRepository.findAllByIsActiveTrue();
		List<AccountInfoDto> accountInfoDtos = this.getAccountInfos(accountInfos);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", accountInfoDtos);

	}

	private List<AccountInfoDto> getAccountInfos(List<AccountInfo> accountInfos) {
		List<AccountInfoDto> accountInfoDtos = new ArrayList<>();
		accountInfos.forEach(accountInfo -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			AccountInfoDto accountInfoDto = modelMapper.map(accountInfo, AccountInfoDto.class);
			accountInfoDtos.add(accountInfoDto);
		});
		return accountInfoDtos;
	}

}
