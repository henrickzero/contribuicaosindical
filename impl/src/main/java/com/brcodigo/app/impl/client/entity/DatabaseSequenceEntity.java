package com.brcodigo.app.impl.client.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("DatabaseSequence")
@Data
public class DatabaseSequenceEntity {

    @Id
    private String id;
    private long seq;
}