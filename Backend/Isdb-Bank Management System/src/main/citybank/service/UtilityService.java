package com.citybank.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citybank.dto.Response;
import com.citybank.model.BaseModel;

public interface UtilityService {

	<B extends BaseModel, R extends JpaRepository<B, Long>> Response getCreateResponse(B b, R r);

	<B extends BaseModel> Response getNoContentResponse(B b);

	<B extends BaseModel, R extends JpaRepository<B, Long>, O extends Object> Response getUpdateResponse(B b, R r, O o);

	<O extends Object> Response getGetResponse(O o, String root);

	<B extends BaseModel, R extends JpaRepository<B, Long>> Response deleteEntityResponse(B b, R r);

}
