package com.brcodigo.app.impl.user.repository;

import com.brcodigo.app.impl.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository1 extends MongoRepository<UserEntity, String> {
    UserEntity findByName(String name);

    UserEntity findByEmail(String email);

    UserEntity findByNameOrEmail(String name, String email);

    UserEntity findByNameOrEmailAndPassword(String name, String email, String password);
}
