package com.brcodigo.app.impl.client.entity;

import com.brcodigo.app.impl.client.enums.ParentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContactEmergencyEntity {
    @NotNull(message = "O campo 'name' é obrigatório")
    private String name;
    @NotNull(message = "O campo 'phoneNumber' é obrigatório")
    private String phoneNumber;
    @NotNull(message = "O campo 'preference' é obrigatório")
    private Boolean preference;
    @NotNull(message = "O campo 'parentType' é obrigatório")
    private ParentTypeEnum parentType;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
}
