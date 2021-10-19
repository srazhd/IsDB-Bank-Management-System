package com.citybank.service;
 
import com.citybank.dto.Response;
import com.citybank.dto.UserDto;
import com.citybank.model.User;
 

public interface UserService {
	Response save(UserDto userDto);
    Response update(Long id, UserDto userDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
    User get(String username);
}
