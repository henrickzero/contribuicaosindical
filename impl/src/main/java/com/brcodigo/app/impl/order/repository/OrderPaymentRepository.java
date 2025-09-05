package com.brcodigo.app.impl.order.repository;

import com.brcodigo.app.impl.order.entity.OrderPaymentEntity;
import com.brcodigo.app.impl.user.UserService1;
import com.mongodb.client.MongoClient;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderPaymentRepository {
    private final UserService1 userService1;
    private final MongoClient mongoClient;

    private MongoTemplate database() {
        return new MongoTemplate(new SimpleMongoClientDbFactory(mongoClient, userService1.getCurrentUser().getDatabase()));
    }

    public OrderPaymentEntity save(OrderPaymentEntity orderPaymentEntity) {
        return database().save(orderPaymentEntity);
    }

    public OrderPaymentEntity findById(String id) {
        return database().findById(id, OrderPaymentEntity.class);
    }

}
