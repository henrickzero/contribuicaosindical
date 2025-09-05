package com.brcodigo.app.impl.client.repository;

import com.brcodigo.app.impl.client.entity.ClientEntity;
import com.brcodigo.app.impl.client.entity.DatabaseSequenceEntity;
import com.brcodigo.app.impl.user.UserService1;
import com.mongodb.client.MongoClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@AllArgsConstructor
public class ClientRepository {
    private final UserService1 userService1;
    private final MongoClient mongoClient;

    private MongoTemplate database() {
        return new MongoTemplate(new SimpleMongoClientDbFactory(mongoClient, userService1.getCurrentUser().getDatabase()));
    }

    public ClientEntity save(ClientEntity clientEntity) {
        return database().save(clientEntity);
    }
    public void remove(ClientEntity clientEntity) {
        database().remove(clientEntity);
    }

    public ClientEntity findById(String id) {
        return database().findById(id, ClientEntity.class);
    }

    public List<ClientEntity> findAll() {
        return database().findAll(ClientEntity.class);
    }

    public Page<ClientEntity> findLike(String text, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        // Usando expressão regular para simular 'LIKE'
        // 'i' para ignorar maiúsculas e minúsculas
        Query query = new Query(new Criteria().orOperator(
                Criteria.where("name").regex(text, "i"),
                Criteria.where("registration").is( toLong(text)),
                Criteria.where("document").regex(text, "i"),
                Criteria.where("rg").regex(text, "i"),
                Criteria.where("emailMain").regex(text, "i")
        )).with(pageable);

        List<ClientEntity> lista = database().find(query, ClientEntity.class);
        // Para retornar um objeto Page, você precisa também contar o total de elementos
        long total = database().count(Query.of(query).limit(-1).skip(-1), ClientEntity.class);

        return new PageImpl<>(lista, pageable, total);
    }

    private Long toLong(String text){
        try{
            return Long.parseLong(text);
        }catch (Exception e){
            return null;
        }
    }

    public List<ClientEntity> findLastTen() {
        Query query = new Query()
                .with(Sort.by(Sort.Direction.DESC, "updateDate"))
                .limit(10);
        return database().find(query, ClientEntity.class);
    }

    public ClientEntity getById(String id) {
        return database().findById(id, ClientEntity.class);
    }

    public Long generateSequence(String seqName) {
        DatabaseSequenceEntity counter = database().findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                DatabaseSequenceEntity.class);
        return counter != null ? counter.getSeq() : 1;
    }


}
