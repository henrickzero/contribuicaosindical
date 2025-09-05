package com.brcodigo.app.impl.client;

import com.brcodigo.app.impl.client.entity.ClientEntity;
import com.brcodigo.app.impl.client.mapper.ClientEntityMapper;
import com.brcodigo.app.impl.client.repository.ClientRepository;
import com.brcodigo.app.impl.error.AppException;
import com.brcodigo.app.impl.user.UserService1;
import com.brcodigo.app.impl.util.DateUtil;
import com.brcodigo.app.impl.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final UserService1 userService1;
    private final ClientRepository clientRepository;
    private final Validator validator;

    public ClientEntity createClient(ClientEntity clientEntity) {
        clientEntity.setId(null);
        clientEntity.setCreateUserId(userService1.getCurrentUser().getUser().getId());
        clientEntity.setCreateDate(LocalDateTime.now());
        clientEntity.setUpdateDate(LocalDateTime.now());
        clientEntity.setSubsidiaryId(userService1.getCurrentUser().getSubsidiaryId());
        clientEntity.setUpdateUserId(userService1.getCurrentUser().getUser().getId());
        clientEntity.setRegistration(clientRepository.generateSequence("registration"));
        return save(clientEntity);
    }

    public ClientEntity updateClient(ClientEntity clientEntity, String token) {
        ClientEntity clientEntityFound = clientRepository.findById(clientEntity.getId());
        validToken(clientEntityFound, token);
        ClientEntity clientEntityUpdate = ClientEntityMapper.INSTANCE.mapEntityToUpdate(clientEntity);

        clientEntityUpdate.setId(clientEntityFound.getId());
        clientEntityUpdate.setCreateUserId(clientEntityFound.getCreateUserId());
        clientEntityUpdate.setCreateDate(clientEntityFound.getCreateDate());
        clientEntityUpdate.setUpdateDate(LocalDateTime.now());
        clientEntityUpdate.setSubsidiaryId(clientEntityFound.getSubsidiaryId());
        clientEntityUpdate.setUpdateUserId(userService1.getCurrentUser().getUser().getId());
        clientEntityUpdate.setRegistration(clientEntityFound.getRegistration());

        return save(clientEntityUpdate);
    }

    public void removeClient(String id, String token) {
        ClientEntity clientEntityFound = clientRepository.findById(id);
        validToken(clientEntityFound, token);
        remove(clientEntityFound);
    }

    private void validToken(ClientEntity clientEntityFound, String token) {
        if (!SecurityUtil.generateHash(
                        clientEntityFound.getId(),
                        DateUtil.localDateTimeToString(clientEntityFound.getUpdateDate()))
                .equals(token)) {
            throw new AppException("O registro foi modificado por outro usuário. Por gentileza, atualize a página e tente novamente.");
        }
    }

    public void remove(ClientEntity clientEntity) {
        clientRepository.remove(clientEntity);
    }

    public ClientEntity save(ClientEntity clientEntity) {
        valid(clientEntity);
        return clientRepository.save(clientEntity);
    }

    public void valid(ClientEntity clientEntity) {
        Set<ConstraintViolation<ClientEntity>> violations = validator.validate(clientEntity);
        if (!violations.isEmpty()) {
            ConstraintViolation<ClientEntity> violation = violations.iterator().next();
            throw new RuntimeException(violation.getMessage());
        }
    }

    public ClientEntity getByCpf(String cpf) {
        return null;
    }

    public List<ClientEntity> getClients() {
        return clientRepository.findAll();
    }

    public List<ClientEntity> getLastTenClients() {
        return clientRepository.findLastTen();
    }

    public ClientEntity getById(String id) {
        return clientRepository.getById(id);
    }

    public Page<ClientEntity> getByFilter(String text, Integer page, Integer size) {
        if (ObjectUtils.isEmpty(text)) {
            return null;
        }
        return clientRepository.findLike(text, page, size);
    }

}
