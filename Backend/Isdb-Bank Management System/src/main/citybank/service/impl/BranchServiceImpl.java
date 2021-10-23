package com.citybank.service.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.citybank.dto.BranchDto;
import com.citybank.dto.Response;
import com.citybank.model.Branch;
import com.citybank.repository.BranchRepository;
import com.citybank.service.BranchService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import java.util.List;

@Service("branchService")
public class BranchServiceImpl implements BranchService {
	private final BranchRepository branchRepository;
	private final ModelMapper modelMapper;
	private final HikariDataSource dataSource;
	private final String root = "Branch";

	public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, HikariDataSource dataSource) {
		this.branchRepository = branchRepository;

		this.modelMapper = modelMapper;
		this.dataSource = dataSource;
	}

	@Override
	public Response save(BranchDto branchDto) {
		Branch branch = modelMapper.map(branchDto, Branch.class);
		branch = branchRepository.save(branch);
		if (branch != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created Successfully", null);
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
	}

	@Override
	public Response update(Long id, BranchDto branchDto) {
		Branch branch = branchRepository.findByIdAndIsActiveTrue(id);

		if (branch != null) {

			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(branchDto, branch);

			branch = branchRepository.save(branch);
			if (branch != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
			}

			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response delete(Long id) {
		Branch branch = branchRepository.findByIdAndIsActiveTrue(id);
		if (branch != null) {
			branch.setIsActive(false);
			branch = branchRepository.save(branch);
			if (branch != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response get(Long id) {
		Branch branch = branchRepository.findByIdAndIsActiveTrue(id);
		if (branch != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			BranchDto branchDto = modelMapper.map(branch, BranchDto.class);
			if (branch != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", branchDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response getAll() {
		List<Branch> branchs = branchRepository.findAllByIsActiveTrue();
		List<BranchDto> branchDtos = this.getBranchs(branchs);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", branchDtos);

	}

	private List<BranchDto> getBranchs(List<Branch> branchs) {
		List<BranchDto> branchDtos = new ArrayList<>();
		branchs.forEach(branch -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			BranchDto branchDto = modelMapper.map(branch, BranchDto.class);
			branchDtos.add(branchDto);
		});
		return branchDtos;
	}

}
