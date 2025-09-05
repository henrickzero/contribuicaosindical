package com.brcodigo.app.impl.sankey.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sankey_data")
@Data
public class SankeyData {
    @Id
    private String id; // Using String for MongoDB ObjectId
    private String source;
    private String target;
    private Integer value;
}