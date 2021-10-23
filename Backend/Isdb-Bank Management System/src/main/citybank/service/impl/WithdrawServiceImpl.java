package com.citybank.service.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.citybank.dto.WithdrawDto;
import com.citybank.dto.Response;
import com.citybank.model.AccountInfo;
import com.citybank.model.Withdraw;
import com.citybank.repository.WithdrawRepository;
import com.citybank.service.WithdrawService;
import com.citybank.service.WithdrawService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List;

@Service("withdrawService")
public class WithdrawServiceImpl implements WithdrawService {
	private final WithdrawRepository withdrawRepository;
	private final ModelMapper modelMapper;
	private final HikariDataSource dataSource;
	private final String root = "Withdraw";

	public WithdrawServiceImpl(WithdrawRepository withdrawRepository, ModelMapper modelMapper,
			HikariDataSource dataSource) {
		this.withdrawRepository = withdrawRepository;

		this.modelMapper = modelMapper;
		this.dataSource = dataSource;
	}

	@Override
	public Response save(WithdrawDto withdrawDto) {
		Withdraw withdraw = modelMapper.map(withdrawDto, Withdraw.class);
		withdraw = withdrawRepository.save(withdraw);
		if (withdraw != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created Successfully", null);
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
	}

	@Override
	public Response update(Long id, WithdrawDto withdrawDto) {
		Withdraw withdraw = withdrawRepository.findByIdAndIsActiveTrue(id);
		withdraw.setAccountInfo(new AccountInfo());
		if (withdraw != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(withdrawDto, withdraw);

			withdraw = withdrawRepository.save(withdraw);
			if (withdraw != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
			}

			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response delete(Long id) {
		Withdraw withdraw = withdrawRepository.findByIdAndIsActiveTrue(id);
		if (withdraw != null) {
			withdraw.setIsActive(false);
			withdraw = withdrawRepository.save(withdraw);
			if (withdraw != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response get(Long id) {
		Withdraw withdraw = withdrawRepository.findByIdAndIsActiveTrue(id);
		if (withdraw != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			WithdrawDto withdrawDto = modelMapper.map(withdraw, WithdrawDto.class);
			if (withdraw != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", withdrawDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response getAll() {
		List<Withdraw> withdraws = withdrawRepository.findAllByIsActiveTrue();
		List<WithdrawDto> withdrawDtos = this.getWithdraws(withdraws);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", withdrawDtos);

	}

	private List<WithdrawDto> getWithdraws(List<Withdraw> withdraws) {
		List<WithdrawDto> withdrawDtos = new ArrayList<>();
		withdraws.forEach(withdraw -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			WithdrawDto withdrawDto = modelMapper.map(withdraw, WithdrawDto.class);
			withdrawDtos.add(withdrawDto);
		});
		return withdrawDtos;
	}

}
