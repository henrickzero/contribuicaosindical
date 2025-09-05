package com.brcodigo.app.impl.user.repository;

import com.brcodigo.app.impl.user.entity.SubsidiaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubsidiaryRepository extends MongoRepository<SubsidiaryEntity, String> {

    SubsidiaryEntity getById(String id);
}
