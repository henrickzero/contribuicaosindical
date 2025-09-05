package com.brcodigo.app.impl.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Data
public class Task {
    @Id
    private Long id;

    private String title;

    private String due;

    private String desc;

    private Integer quadrant;

    private Boolean completed;

    private String createdAt;

    private String importance;

    private String urgency;
}