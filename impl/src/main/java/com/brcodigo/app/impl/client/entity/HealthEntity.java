package com.brcodigo.app.impl.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HealthEntity {
    @NotNull(message = "O campo 'healthPlan' é obrigatório")
    private Boolean healthPlan;
    private String doctorName;
    private String doctorPhoneNumber;
    private LocalDate medicalCertificateIssuerDate;
    private LocalDate medicalCertificateValidateDate;
    private LocalDate medicalCertificateLimitDate;
    private LocalDate functionalIssuerDate;
    private LocalDate functionalValidateDate;
    private LocalDate dermatologicalIssuerDate;
    private LocalDate dermatologicalValidateDate;
}
