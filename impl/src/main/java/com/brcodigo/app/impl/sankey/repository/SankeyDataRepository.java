package com.brcodigo.app.impl.sankey.repository;

import com.brcodigo.app.impl.sankey.model.SankeyData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SankeyDataRepository extends MongoRepository<SankeyData, String> {

    List<SankeyData> findBySource(String target);
}