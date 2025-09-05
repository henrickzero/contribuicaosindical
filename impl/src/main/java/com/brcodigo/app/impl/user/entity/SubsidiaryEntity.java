package com.brcodigo.app.impl.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("Subsidiary")
public class SubsidiaryEntity {
    @Id
    private String id;
    @NotNull(message = "O campo 'database' é obrigatório")
    private String database;
    @NotNull(message = "O campo 'companyId' é obrigatório")
    private String companyId;
    @NotNull(message = "O campo 'subsidiaryName' é obrigatório")
    private String subsidiaryName;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
    @NotNull(message = "O campo 'updateDate' é obrigatório")
    private LocalDateTime updateDate;
    private String updateUserId;
}
