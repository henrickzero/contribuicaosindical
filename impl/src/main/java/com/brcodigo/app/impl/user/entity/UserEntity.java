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
@Document("User")
public class UserEntity {
    @Id
    private String id;
    @NotNull(message = "O campo 'name' é obrigatório")
    private String name;
    @NotNull(message = "O campo 'password' é obrigatório")
    private String password;
    @NotNull(message = "O campo 'email' é obrigatório")
    private String email;
    @NotNull(message = "O campo 'companyId' é obrigatório")
    private String companyId;
    @NotNull(message = "O campo 'subsidiaryId' é obrigatório")
    private List<SubsidiaryDatabase> subsidiaries;
    @NotNull(message = "O campo 'database' é obrigatório")
    private LocalDateTime createDate;
    @NotNull(message = "O campo 'updateDate' é obrigatório")
    private LocalDateTime updateDate;
    @NotNull(message = "O campo 'appUserTypeEnum' é obrigatório")
    private AppUserTypeEnum appUserTypeEnum;
}
