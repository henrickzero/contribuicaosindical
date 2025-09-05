package com.brcodigo.app.contract.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @Email(message = "O Email informado é inválido")
    @Size(min = 4,  message = "O Email não foi informado")
    @Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(description = "Senha", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 50, message = "Senha deve conter entre 8 e 50 caracteres")
    private String password;
}