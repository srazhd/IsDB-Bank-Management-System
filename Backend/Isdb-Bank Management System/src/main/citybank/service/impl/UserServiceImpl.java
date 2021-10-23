package com.citybank.service.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.model.User;
import com.citybank.repository.UserRepository;
import com.citybank.service.UserService;
import com.citybank.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final HikariDataSource dataSource;
	private final String root = "User";

	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper,
			HikariDataSource dataSource) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.dataSource = dataSource;
		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public Response save(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user = userRepository.save(user);
		if (user != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created Successfully", null);
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
	}

	@Override
	public Response update(Long id, UserDto userDto) {
		User user = userRepository.findByIdAndIsActiveTrue(id);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(userDto, user);
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user = userRepository.save(user);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated Successfully", null);
			}

			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response delete(Long id) {
		User user = userRepository.findByIdAndIsActiveTrue(id);
		if (user != null) {
			user.setIsActive(false);
			user = userRepository.save(user);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response get(Long id) {
		User user = userRepository.findByIdAndIsActiveTrue(id);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto userDto = modelMapper.map(user, UserDto.class);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", userDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response getAll() {
		List<User> users = userRepository.findAllByIsActiveTrue();
		List<UserDto> userDtos = this.getUsers(users);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", userDtos);

	}

	private List<UserDto> getUsers(List<User> users) {
		List<UserDto> userDtos = new ArrayList<>();
		users.forEach(user -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto userDto = modelMapper.map(user, UserDto.class);
			userDtos.add(userDto);
		});
		return userDtos;
	}

	@Override
	public User get(String username) {
		return userRepository.findByUsernameAndIsActiveTrue(username);
	}

}
