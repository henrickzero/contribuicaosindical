package com.brcodigo.app.impl.user.entity;

import com.brcodigo.app.impl.user.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryDatabase {
    @NotNull(message = "O campo 'subsidiaryId' é obrigatório")
    private String subsidiaryId;
    @NotNull(message = "O campo 'database' é obrigatório")
    private String database;
    @NotNull(message = "O campo 'userType' é obrigatório")
    private UserTypeEnum userType;
}
