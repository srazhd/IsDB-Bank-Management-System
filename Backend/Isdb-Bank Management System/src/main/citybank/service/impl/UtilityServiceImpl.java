package com.citybank.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.citybank.dto.Response;
import com.citybank.jsondiff.JsonDiffDto;
import com.citybank.jsondiff.JsonDiffUtil;
import com.citybank.model.ActionLog;
import com.citybank.model.BaseModel;
import com.citybank.model.User;
import com.citybank.repository.ActionRepository;
import com.citybank.repository.UserRepository;
import com.citybank.service.UtilityService;
import com.citybank.util.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilityServiceImpl implements UtilityService {
	private final UserRepository userRepository;
	private static final Logger logger = LogManager.getLogger(UtilityServiceImpl.class.getName());
	private final ModelMapper modelmapper;
	private final ActionRepository actionRepository;

	public UtilityServiceImpl(UserRepository userRepository, ModelMapper modelmapper,
			ActionRepository actionRepository) {
		super();
		this.userRepository = userRepository;
		this.modelmapper = modelmapper;
		this.actionRepository = actionRepository;
	}

	@Override
	public <B extends BaseModel, R extends JpaRepository<B, Long>> Response getCreateResponse(B b, R r) {
		if (getAuthenticatedUser() != null) {
			b.setCreatedBy(getAuthenticatedUser().getName());
		} else {
			b.setCreatedBy("");
		}
		b = r.save(b);

		return getResponse(b, "created successfully", "created", null);
	}

	@Override
	public <B extends BaseModel> Response getNoContentResponse(B b) {
		if (b == null) {
			return ResponseBuilder.getFailureResponse(HttpStatus.NO_CONTENT, String.format("%s is not active", null));
		}
		return null;
	}

	@Override
	public <B extends BaseModel, R extends JpaRepository<B, Long>, O> Response getUpdateResponse(B b, R r, O o) {
		B oldB = r.findById(getId(b)).get();
		try {
			String oldJson = getJsonString(oldB);
			modelmapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelmapper.map(o, b);
			b.setUpdatedBy(getAuthenticatedUser().getName());
			b = r.save(b);
			return getResponse(b, "update successfully", "updated", oldJson);
		} catch (JsonProcessingException e) {
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}

	@Override
	public <O> Response getGetResponse(O o, String root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <B extends BaseModel, R extends JpaRepository<B, Long>> Response deleteEntityResponse(B b, R r) {
		B oldB = r.findById(getId(b)).get();
		try {
			String oldJson = getJsonString(oldB);
			b.setIsActive(false);
			b.setUpdatedBy(getAuthenticatedUser().getName());
			b = r.save(b);
			return getResponse(b, "deleted successfully", "deleted", oldJson);
		} catch (Exception e) {
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}

	private <B extends BaseModel> Response getResponse(B b, String successMessage, String actionMessage,
			String oldJson) {
		if (b != null) {
			setActionLog(b, actionMessage, oldJson);
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, b.getClass().getName() + successMessage,
					null);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
	}

	private <B extends BaseModel> void setActionLog(B b, String actionMessage, String oldJson) {
		User user = getAuthenticatedUser();
		try {
			String description = b.getClass().getSimpleName() + " " + actionMessage;
			if (oldJson != null) {
				String newJson = getJsonString(b);
				JsonDiffDto jsonDiffDto = JsonDiffUtil.getJsonDiffDto(oldJson, newJson);
				description = new ObjectMapper().writeValueAsString(jsonDiffDto);
			}
			String email = "";
			if (user != null) {
				email = user.getEmail();
			}
			ActionLog log = new ActionLog(email, description, actionMessage, getId(b));
			actionRepository.save(log);
		} catch (Exception e) {
			logger.error("Exception in action log " + e.getMessage());
		}
	}

	private <B extends BaseModel, R extends JpaRepository<B, Long>> Class getReturnType(R r) {
		return getReturnType(r, "findByIdAndIsActiveTrue");
	}

	private <B extends BaseModel> Long getId(B b) {
		Method getIdMethod = null;
		try {
			getIdMethod = getMethod(b, "getId");
		} catch (Exception e) {
			logger.error("Reflaction Exception");
		}
		try {
			Long id = (Long) getIdMethod.invoke(b);
			if (id != null) {
				return id;
			}
		} catch (IllegalAccessException e) {
			logger.error("Method invoke access exception");
			return null;
		} catch (InvocationTargetException e) {
			logger.error("Method invoke target not found exception");
			return null;
		}
		return null;
	}

	private String getJsonString(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(o);
	}

	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return userRepository.findByUsernameAndIsActiveTrue(authentication.getName());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
		}
	}

	public static Method getMethod(Object obj, String methodName) {

		Method method = null;

		try {
			method = obj.getClass().getMethod(methodName); // Non static
		} catch (Exception e) {
		}
		method.setAccessible(true);// To access private method
		return method;

	}

	public static Class getReturnType(Object obj, String methodName) {
		Method method = getMethod(obj, methodName);
		Class returnType = method.getReturnType();
		return returnType;
	}

}
