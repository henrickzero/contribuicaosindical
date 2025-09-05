package com.brcodigo.app.contract.user.mapper;

import com.brcodigo.app.contract.user.model.response.SubsidiaryResponse;
import com.brcodigo.app.impl.user.model.SubsidiaryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubsidiaryMapper {
    SubsidiaryMapper INSTANCE = Mappers.getMapper(SubsidiaryMapper.class);

    List<SubsidiaryResponse> mapToSubsidiaryResponseModelList(List<SubsidiaryResponseModel> subsidiaryResponseModel);

}
