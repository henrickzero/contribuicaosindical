package com.brcodigo.app.impl.user.model;

import com.brcodigo.app.impl.user.entity.CompanyEntity;
import com.brcodigo.app.impl.user.entity.SubsidiaryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyModel {
    private CompanyEntity company;
    private SubsidiaryEntity subsidiary;
    private UserModel user;
}
