package com.brcodigo.app.impl.user.entity;

import com.brcodigo.app.impl.user.enums.AppUserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document("UserAuth")
public class UserAuthEntity {
    @Id
    private String id;
    private String userId;
    private LocalDateTime createDate;
    private Boolean expired;
    private String companyId;
    private List<SubsidiaryDatabase> subsidiaries;
    @NotNull(message = "O campo 'appUserTypeEnum' é obrigatório")
    private AppUserTypeEnum appUserTypeEnum;
}
