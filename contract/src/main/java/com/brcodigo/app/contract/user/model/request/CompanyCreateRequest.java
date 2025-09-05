package com.brcodigo.app.contract.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateRequest {

    @Schema(description = "Senha", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 50, message = "Senha deve conter entre 8 e 50 caracteres")
    private String password;
    @Schema(description = "Repita a Senha", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 50, message = "Repita Senha deve conter entre 8 e 50 caracteres")
    private String rePassword;
    @Email(message = "O Email informado é inválido")
    @NotNull(message = "O Email informado é inválido")
    @Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Size(min = 3, max = 100, message = "Nome da ONG deve conter entre 3 e 100 caracteres")
    @Schema(description = "Nome da ONG", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyName;

}