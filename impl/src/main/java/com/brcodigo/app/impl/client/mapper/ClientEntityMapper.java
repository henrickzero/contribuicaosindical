package com.brcodigo.app.impl.client.mapper;

import com.brcodigo.app.impl.client.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientEntityMapper {
    ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);

    ClientEntity mapEntityToUpdate(ClientEntity clientEntity);
}
