package com.brcodigo.app.impl.user.repository;

import com.brcodigo.app.impl.user.entity.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {
}
