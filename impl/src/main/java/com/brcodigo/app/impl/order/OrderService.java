package com.brcodigo.app.impl.order;

import com.brcodigo.app.impl.order.entity.OrderEntity;
import com.brcodigo.app.impl.order.entity.OrderItemEntity;
import com.brcodigo.app.impl.order.entity.OrderPaymentEntity;
import com.brcodigo.app.impl.order.enums.OrderPaymentStatusEnum;
import com.brcodigo.app.impl.order.enums.OrderStatusEnum;
import com.brcodigo.app.impl.order.repository.OrderItemRepository;
import com.brcodigo.app.impl.order.repository.OrderPaymentRepository;
import com.brcodigo.app.impl.order.repository.OrderRepository;
import com.brcodigo.app.impl.user.UserService1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final UserService1 userService1;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderPaymentRepository orderPaymentRepository;
    private final Validator validator;

    public OrderEntity createOrder(OrderEntity orderEntity,
                                   List<OrderItemEntity> items,
                                   List<OrderPaymentEntity> payments) {
        orderEntity.setId(null);
        orderEntity.setCreateDate(LocalDateTime.now());
        orderEntity.setUpdateDate(LocalDateTime.now());
        orderEntity.setCreateUserId(userService1.getCurrentUser().getUser().getId());
        orderEntity.setUpdateUserId(userService1.getCurrentUser().getUser().getId());
        orderEntity.setStatus(getStatus(orderEntity, payments));
        orderEntity.setDate(LocalDate.now());
        orderEntity = save(orderEntity);
        List<OrderItemEntity> saveItems = saveItems(orderEntity, items);
        List<OrderPaymentEntity> savePayments = savePayments(orderEntity, payments);
        return orderEntity;
    }

    public List<OrderItemEntity> saveItems(OrderEntity orderEntity, List<OrderItemEntity> items) {
        return items.stream()
                .map(orderItemEntity -> {
                    orderItemEntity.setOrderId(orderEntity.getId());
                    orderItemEntity.setStatus(orderEntity.getStatus());
                    validOrderItem(orderItemEntity);
                    return orderItemRepository.save(orderItemEntity);
                }).collect(Collectors.toList());
    }

    public List<OrderPaymentEntity> savePayments(OrderEntity orderEntity, List<OrderPaymentEntity> payments) {
        return payments.stream()
                .map(paymentEntity -> {
                    paymentEntity.setOrderId(orderEntity.getId());
                    paymentEntity.setPaymentDateTime(getPaymentDate(paymentEntity));
                    validOrderPayment(paymentEntity);
                    return orderPaymentRepository.save(paymentEntity);
                }).collect(Collectors.toList());
    }

    public LocalDateTime getPaymentDate(OrderPaymentEntity orderPaymentEntity) {
        if(OrderPaymentStatusEnum.FINISHED.equals(orderPaymentEntity.getStatus())){
            return LocalDateTime.now();
        }else{
            return null;
        }
    }

    private OrderStatusEnum getStatus(OrderEntity orderEntity, List<OrderPaymentEntity> payments) {
        return Optional.ofNullable(payments)
                .map(payments1 -> {

                            if (payments1.stream()
                                    .filter(orderPaymentEntity -> OrderPaymentStatusEnum.FINISHED.equals(orderPaymentEntity.getStatus()))
                                    .mapToDouble(value -> value.getValue().doubleValue()).sum() == orderEntity.getTotal().doubleValue()) {
                                return OrderStatusEnum.FINISHED;
                            } else {
                                return OrderStatusEnum.PEDING;
                            }
                        }
                )
                .orElse(OrderStatusEnum.PEDING);
    }

    public OrderEntity updateOrder(OrderEntity orderEntity) {
        OrderEntity orderEntityUpdate = orderRepository.findById(orderEntity.getId());
        return save(orderEntityUpdate);
    }

    public OrderEntity save(OrderEntity OrderEntity) {
        validOrder(OrderEntity);
        return orderRepository.save(OrderEntity);
    }

    public void validOrder(OrderEntity orderEntity) {
        Set<ConstraintViolation<OrderEntity>> violations = validator.validate(orderEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<OrderEntity> violation = violations.iterator().next();
            throw new RuntimeException(violation.getMessage());
        }
    }

    public void validOrderItem(OrderItemEntity orderItemEntity) {
        Set<ConstraintViolation<OrderItemEntity>> violations = validator.validate(orderItemEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<OrderItemEntity> violation = violations.iterator().next();
            throw new RuntimeException(violation.getMessage());
        }
    }

    public void validOrderPayment(OrderPaymentEntity orderPaymentEntity) {
        Set<ConstraintViolation<OrderPaymentEntity>> violations = validator.validate(orderPaymentEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<OrderPaymentEntity> violation = violations.iterator().next();
            throw new RuntimeException(violation.getMessage());
        }
    }

    public OrderEntity getById(String id) {
        return null;
    }

}
