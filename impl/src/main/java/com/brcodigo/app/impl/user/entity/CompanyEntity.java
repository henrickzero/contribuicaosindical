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
@Document("Company")
public class CompanyEntity {
    @Id
    private String id;
    @NotNull(message = "O campo 'companyName' é obrigatório")
    private String companyName;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
    @NotNull(message = "O campo 'updateDate' é obrigatório")
    private LocalDateTime updateDate;
    private String updateUserId;
}
