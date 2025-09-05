package com.brcodigo.app.impl.text.entity;

import com.brcodigo.app.impl.text.enums.TextTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("Text")
public class TextEntity {
    @Id
    private String id;
    private String name;
    private String question;
    private String answer;
    private String extra;
    private String full;
    private TextTypeEnum textType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    private Boolean hasAudio;
    private Boolean hasVideo;
    private Boolean uploadComplete;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate uploadDate;
}
