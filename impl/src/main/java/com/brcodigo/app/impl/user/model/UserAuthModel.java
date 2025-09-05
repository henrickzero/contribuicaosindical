package com.brcodigo.app.impl.user.model;

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
public class UserAuthModel {
    private UserModel user;
    private List<String> rules;
    private String token;
    private LocalDateTime createDate;
    private UserTypeEnum userType;
    private String subsidiaryId;
    private String database;
}
