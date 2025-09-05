package com.brcodigo.app.impl.user.repository;

import com.brcodigo.app.impl.user.entity.UserAuthEntity;
import com.brcodigo.app.impl.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuthRepository extends MongoRepository<UserAuthEntity, String> {
    UserEntity findByUserId(String userId);
}
