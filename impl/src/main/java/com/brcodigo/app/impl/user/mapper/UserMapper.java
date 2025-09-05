package com.brcodigo.app.impl.user.mapper;

import com.brcodigo.app.impl.user.entity.SubsidiaryDatabase;
import com.brcodigo.app.impl.user.entity.UserAuthEntity;
import com.brcodigo.app.impl.user.entity.UserEntity;
import com.brcodigo.app.impl.user.enums.AppUserTypeEnum;
import com.brcodigo.app.impl.user.model.UserAuthModel;
import com.brcodigo.app.impl.user.model.UserModel;
import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {

    public static UserEntity mapToEntity(AppUserTypeEnum appUserTypeEnum, UserModel userModel) {
        return UserEntity.builder()
                .name(userModel.getEmail())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .createDate(LocalDateTime.now())
                .companyId(userModel.getCompanyId())
                .subsidiaries(userModel.getSubsidiaries())
                .updateDate(LocalDateTime.now())
                .appUserTypeEnum(appUserTypeEnum)
                .build();
    }

    public static UserModel mapToModel(UserEntity userEntity) {
        return UserModel.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .createDate(userEntity.getCreateDate())
                .build();
    }

    public static UserAuthModel mapToUserAuthModel(UserAuthEntity userAuthEntity, UserEntity userEntity) {
        return UserAuthModel.builder()
                .user(mapToModel(userEntity))
                .token(userAuthEntity.getId())
                .createDate(userAuthEntity.getCreateDate())
                .build();
    }

    public static UserAuthEntity mapToUserAuthEntity(UserEntity userEntity) {
        return UserAuthEntity.builder()
                .userId(userEntity.getId())
                .createDate(LocalDateTime.now())
                .expired(false)
                .companyId(userEntity.getCompanyId())
                .subsidiaries(userEntity.getSubsidiaries())
                .appUserTypeEnum(userEntity.getAppUserTypeEnum())
                .build();
    }

    public static UserAuthModel mapToUserAuthEntity(UserAuthEntity userAuthEntity, String subsidiaryId) {
        SubsidiaryDatabase subsidiaryDatabase = getSubsidiaryDatabase(userAuthEntity.getSubsidiaries(), subsidiaryId);
        String database = null;
        List<String> rules = new ArrayList<>(userAuthEntity.getAppUserTypeEnum().getRules());
        if(!ObjectUtils.isEmpty(subsidiaryDatabase)){
            rules.addAll(subsidiaryDatabase.getUserType().getRules());
            database = subsidiaryDatabase.getDatabase();
        }
        return UserAuthModel.builder()
                .rules(rules)
                .database(database)
                .user(UserModel.builder()
                        .id(userAuthEntity.getUserId())
                        .companyId(userAuthEntity.getCompanyId())
                        .subsidiaries(userAuthEntity.getSubsidiaries())
                        .build())
                .token(userAuthEntity.getId())
                .build();
    }

    public static SubsidiaryDatabase getSubsidiaryDatabase(List<SubsidiaryDatabase> subsidiaries, String subsidiaryId) {
        return subsidiaries.stream().filter(subsidiaryDatabase -> subsidiaryDatabase.getSubsidiaryId().equals(subsidiaryId))
                .findFirst()
                .orElse(null);
    }


}
