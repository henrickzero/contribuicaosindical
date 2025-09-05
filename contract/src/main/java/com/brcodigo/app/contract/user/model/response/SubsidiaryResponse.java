package com.brcodigo.app.contract.user.model.response;

import com.brcodigo.app.impl.user.enums.UserTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryResponse {

    @Schema(description = "ID da Ong")
    private String id;
    @Schema(description = "Nome da Ong")
    private String name;
    @Schema(description = "Tipo do Usuário")
    private UserTypeEnum userType;
    @Schema(description = "Data de Criação")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;
}