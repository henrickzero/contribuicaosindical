package com.brcodigo.app.impl.user.model;

import com.brcodigo.app.impl.user.entity.SubsidiaryDatabase;
import com.brcodigo.app.impl.user.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String id;
    private String name;
    private String password;
    private String email;
    private String companyId;
    private List<SubsidiaryDatabase> subsidiaries;
    private UserTypeEnum userType;
    private LocalDateTime createDate;
}
