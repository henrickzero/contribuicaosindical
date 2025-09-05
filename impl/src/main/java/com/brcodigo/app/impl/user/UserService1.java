package com.brcodigo.app.impl.user;

import com.brcodigo.app.impl.error.AccessException;
import com.brcodigo.app.impl.error.AppException;
import com.brcodigo.app.impl.user.entity.*;
import com.brcodigo.app.impl.user.enums.AppUserTypeEnum;
import com.brcodigo.app.impl.user.enums.UserTypeEnum;
import com.brcodigo.app.impl.user.mapper.UserMapper;
import com.brcodigo.app.impl.user.model.CreateCompanyModel;
import com.brcodigo.app.impl.user.model.SubsidiaryResponseModel;
import com.brcodigo.app.impl.user.model.UserAuthModel;
import com.brcodigo.app.impl.user.model.UserModel;
import com.brcodigo.app.impl.user.repository.CompanyRepository;
import com.brcodigo.app.impl.user.repository.SubsidiaryRepository;
import com.brcodigo.app.impl.user.repository.UserAuthRepository;
import com.brcodigo.app.impl.user.repository.UserRepository1;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class UserService1 {

    private final UserRepository1 userRepository1;
    private final UserAuthRepository userAuthRepository;
    private final CompanyRepository companyRepository;
    private final SubsidiaryRepository subsidiaryRepository;
    private final MongoTemplate mongoTemplate;
    private final Validator validator;
    private final UserAuthModel currentUser;

    public UserAuthModel createCompany(CreateCompanyModel createCompanyModel) {
        CompanyEntity companyEntity = createCompany(createCompanyModel.getCompany());
        SubsidiaryEntity subsidiary = createCompanyModel.getSubsidiary();
        subsidiary.setCompanyId(companyEntity.getId());
        SubsidiaryEntity subsidiaryEntity = createSubsidiaryWithCompany(subsidiary);

        UserModel user = createCompanyModel.getUser();
        user.setCompanyId(companyEntity.getId());
        user.setSubsidiaries(List.of(SubsidiaryDatabase.builder()
                .subsidiaryId(subsidiaryEntity.getId())
                        .userType(UserTypeEnum.COMPANY)
                .database(subsidiaryEntity.getDatabase()).build()));
        return createUserWithCompany(user);
    }

    private CompanyEntity createCompany(CompanyEntity companyEntity) {
        companyEntity.setId(null);
        companyEntity.setCreateDate(LocalDateTime.now());
        companyEntity.setUpdateDate(LocalDateTime.now());
        validCompany(companyEntity);
        return companyRepository.save(companyEntity);
    }

    private SubsidiaryEntity createSubsidiaryWithCompany(SubsidiaryEntity subsidiaryEntity) {
        subsidiaryEntity.setId(null);
        subsidiaryEntity.setCreateDate(LocalDateTime.now());
        subsidiaryEntity.setUpdateDate(LocalDateTime.now());
        subsidiaryEntity.setDatabase(generateRandomIdentifier());
        validSubsidiary(subsidiaryEntity);
//        if (ObjectUtils.isEmpty(getSubsidiaryByDocument(subsidiaryEntity.getSubsidiaryDocument(),
//                subsidiaryEntity.getCompanyId()))) {
            return subsidiaryRepository.save(subsidiaryEntity);
//        } else {
//            throw new AppException("Filial/Unidade já cadastrada");
//        }
    }

    private UserAuthModel createUserWithCompany(UserModel userModel) {
        if (ObjectUtils.isEmpty(userRepository1.findByNameOrEmail(userModel.getName(), userModel.getEmail()))) {
            UserEntity userEntity = UserMapper.mapToEntity(AppUserTypeEnum.USER, userModel);
            validUser(userEntity);
            userRepository1.save(userEntity);
            return login(userModel);
        } else {
            throw new AppException("Usuário já cadatsrado");
        }
    }

     public UserAuthModel create(UserModel userModel) {
        if (ObjectUtils.isEmpty(userRepository1.findByNameOrEmail(userModel.getName(), userModel.getEmail()))) {
            userRepository1.save(UserMapper.mapToEntity(AppUserTypeEnum.USER, userModel));
            return login(userModel);
        } else {
            throw new AppException("Usuário já cadatsrado");
        }
    }


    public UserAuthModel login(UserModel userModel) {
        UserEntity userEntity = userRepository1.findByNameOrEmailAndPassword(
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword());

        if (!ObjectUtils.isEmpty(userEntity)) {
            UserAuthEntity userAuthEntity = userAuthRepository.save(UserMapper.mapToUserAuthEntity(userEntity));
            return UserMapper.mapToUserAuthModel(userAuthEntity, userEntity);
        } else {
            throw new AppException("Usuário não encontrado");
        }
    }

    public UserAuthModel getAuth(String token, String subsidiaryId) {
        return userAuthRepository.findById(token)
                .map(userAuthEntity ->  UserMapper.mapToUserAuthEntity(userAuthEntity, subsidiaryId))
                .orElse(UserAuthModel.builder()
                        .rules(List.of())
                        .build());
    }

    public List<CompanyEntity> getCompanyByDocument(String companyDocument) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("companyDocument").is(companyDocument)
        );
        return mongoTemplate.find(query, CompanyEntity.class);
    }

    public List<SubsidiaryEntity> getSubsidiaryByDocument(String subsidiaryDocument, String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("subsidiaryDocument").is(subsidiaryDocument)
                .and("companyId").is(companyId)
        );
        return mongoTemplate.find(query, SubsidiaryEntity.class);
    }

    public void validCompany(CompanyEntity companyEntity) {
        Set<ConstraintViolation<CompanyEntity>> violations = validator.validate(companyEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<CompanyEntity> violation = violations.iterator().next();
            throw new AppException(violation.getMessage());
        }
    }

    public void validSubsidiary(SubsidiaryEntity subsidiaryEntity) {
        Set<ConstraintViolation<SubsidiaryEntity>> violations = validator.validate(subsidiaryEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<SubsidiaryEntity> violation = violations.iterator().next();
            throw new AppException(violation.getMessage());
        }
    }

    public void validUser(UserEntity userEntity) {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<UserEntity> violation = violations.iterator().next();
            throw new AppException(violation.getMessage());
        }
    }

    public static String generateRandomIdentifier() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }

    public List<SubsidiaryResponseModel> getSubsidiaries() {
        if(ObjectUtils.isEmpty(currentUser.getUser()) || ObjectUtils.isEmpty(currentUser.getUser().getSubsidiaries())){
            throw new AccessException("O Usuário não possui permissão");
        }
        return currentUser.getUser().getSubsidiaries().stream()
                .map(subsidiaryDatabase -> {
                    SubsidiaryEntity subsidiaryEntity = subsidiaryRepository.getById(subsidiaryDatabase.getSubsidiaryId());
                        return SubsidiaryResponseModel.builder()
                                .name(subsidiaryEntity.getSubsidiaryName())
                                .id(subsidiaryEntity.getId())
                                .userType(subsidiaryDatabase.getUserType())
                                .createDate(subsidiaryEntity.getCreateDate())
                                .build();
                }).collect(Collectors.toList());

    }

    public UserAuthModel getCurrentUser(){
        return currentUser;
    }

}
