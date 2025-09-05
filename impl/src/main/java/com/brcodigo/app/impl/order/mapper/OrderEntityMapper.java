package com.brcodigo.app.impl.order.mapper;

import com.brcodigo.app.impl.order.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderEntityMapper {
    OrderEntityMapper INSTANCE = Mappers.getMapper(OrderEntityMapper.class);

    OrderEntity mapEntityToUpdate(OrderEntity orderEntity);
}
