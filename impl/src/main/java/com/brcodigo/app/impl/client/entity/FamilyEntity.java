package com.brcodigo.app.impl.client.entity;

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
public class FamilyEntity {
    @NotNull(message = "O campo 'registration' é obrigatório")
    private String registration;
    @NotNull(message = "O campo 'createDate' é obrigatório")
    private LocalDateTime createDate;
}
