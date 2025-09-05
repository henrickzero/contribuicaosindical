package com.brcodigo.app.impl.client.entity;

import com.brcodigo.app.impl.client.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("Client")
public class ClientEntity {
    @Id
    private String id;
    private String photo;
    private String document;
    @NotNull(message = "O campo 'clientType' é obrigatório")
    private ClientTypeEnum clientType;
    private DocumentTypeEnum documentType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;
    private String createUserId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDate;
    private String updateUserId;
    private Long registration;
    private String name;
    private String reducerName;
    private String rg;
    private String rgIssuer;
    private Boolean foreigner;
    private LocalDate dateOfBirth;
    private String cityOfBirth;
    private String stateOfBirth;
    private String countryOfBirth;
    private String subsidiaryId;
    private MaritalStatusEnum maritalStatus;
    @NotNull(message = "O campo 'gender' é obrigatório")
    private ProfessionEnum profession;
    @NotNull(message = "O campo 'gender' é obrigatório")
    private GenderEnum gender;
    private String namePersonResponsible;
    private String cpfPersonResponsible;
    private String fathersName;
    private String mothersName;
    private BigDecimal individualIncome;
    private BigDecimal familyIncome;
    private AddressEntity address;
    private List<ContactEntity> contacts;
    private List<ContactEmergencyEntity> contactEmergencies;
    private String emailMain;
    private String emailAlternative;
    private Boolean allowsEmailMarketing;
    private HealthEntity health;
    private String referrerRegistration;
    private List<FamilyEntity> relatives;
    private List<ObservationEntity> observations;
}
