package com.citybank.service.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.citybank.dto.TransferDto;
import com.citybank.dto.Response;
import com.citybank.model.AccountInfo;
import com.citybank.model.Transfer;
import com.citybank.repository.TransferRepository;
import com.citybank.service.TransferService;
import com.citybank.service.TransferService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List;

@Service("transferService")
public class TransferServiceImpl implements TransferService {
	private final TransferRepository transferRepository;
	private final ModelMapper modelMapper;
	private final HikariDataSource dataSource;
	private final String root = "Transfer";

	public TransferServiceImpl(TransferRepository transferRepository, ModelMapper modelMapper,
			HikariDataSource dataSource) {
		this.transferRepository = transferRepository;
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
		this.modelMapper = modelMapper;
		this.dataSource = dataSource;
	}

	@Override
	public Response save(TransferDto transferDto) {
		Transfer transfer = modelMapper.map(transferDto, Transfer.class);
		transfer = transferRepository.save(transfer);
		if (transfer != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created Successfully", null);
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
	}

	@Override
	public Response update(Long id, TransferDto transferDto) {
		Transfer transfer = transferRepository.findByIdAndIsActiveTrue(id);
		transfer.setAccountInfoFrom(new AccountInfo());
		transfer.setAccountInfoTo(new AccountInfo());
		if (transfer != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(transferDto, transfer);

			transfer = transferRepository.save(transfer);
			if (transfer != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
			}

			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response delete(Long id) {
		Transfer transfer = transferRepository.findByIdAndIsActiveTrue(id);
		if (transfer != null) {
			transfer.setIsActive(false);
			transfer = transferRepository.save(transfer);
			if (transfer != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response get(Long id) {
		Transfer transfer = transferRepository.findByIdAndIsActiveTrue(id);
		if (transfer != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			TransferDto transferDto = modelMapper.map(transfer, TransferDto.class);
			if (transfer != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", transferDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response getAll() {
		List<Transfer> transfers = transferRepository.findAllByIsActiveTrue();
		List<TransferDto> transferDtos = this.getTransfers(transfers);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", transferDtos);

	}

	private List<TransferDto> getTransfers(List<Transfer> transfers) {
		List<TransferDto> transferDtos = new ArrayList<>();
		transfers.forEach(transfer -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			TransferDto transferDto = modelMapper.map(transfer, TransferDto.class);
			transferDtos.add(transferDto);
		});
		return transferDtos;
	}

}
