package com.brcodigo.app.contract.user.mapper;

import com.brcodigo.app.contract.user.model.request.CompanyCreateRequest;
import com.brcodigo.app.contract.user.model.request.UserCreateRequest;
import com.brcodigo.app.contract.user.model.request.UserLoginRequest;
import com.brcodigo.app.contract.user.model.response.UserAuthResponse;
import com.brcodigo.app.impl.user.model.UserAuthModel;
import com.brcodigo.app.impl.user.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel mapToModel(UserCreateRequest userCreateRequest);

    UserModel mapToModel(UserLoginRequest userLoginRequest);

    UserAuthResponse mapToResponse(UserAuthModel userAuthModel);

    UserModel mapToModelCompany(CompanyCreateRequest companyCreateRequest);

}
