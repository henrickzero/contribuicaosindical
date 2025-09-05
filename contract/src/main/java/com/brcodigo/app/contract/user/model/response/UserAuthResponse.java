package com.brcodigo.app.contract.user.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponse {

    @Schema(description = "Token")
    private String token;
    @Schema(description = "Data de Criação")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;
    @Schema(description = "Permissões")
    private List<String> rules;
    @Schema(description = "Permissões")
    private UserResponse user;
}