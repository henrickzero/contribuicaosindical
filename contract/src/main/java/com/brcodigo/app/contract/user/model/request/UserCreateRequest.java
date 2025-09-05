package com.brcodigo.app.contract.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    @Schema(description = "Usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @NotNull
    @Schema(description = "Senha", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Email
    @Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Email
    @Schema(description = "ID da Filia/Unidade", requiredMode = Schema.RequiredMode.REQUIRED)
    private String subsidiaryId;
}