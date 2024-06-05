package com.dotsageiv.HomeConnect.infrastructure.gateway.services;

import com.dotsageiv.HomeConnect.core.domain.entities.User;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.UserRepository;

import java.util.UUID;
import java.util.stream.StreamSupport;

public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    public UserService(UserMapper mapper,
                       UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public User create(User domainObj) {
        var mappedEntity = mapper
                .toEntity(domainObj);

        return mapper.toDomainObj(repository
                .save(mappedEntity));
    }

    public Iterable<User> getAll() {
        var entities = repository
                .findAll()
                .spliterator();

        return StreamSupport.stream(entities, false)
                .map(mapper::toDomainObj)
                .toList();
    }

    public User getById(UUID id) {
        var existEntity = repository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Usuário não existe!"));

        return mapper.toDomainObj(existEntity);
    }

    public User updateById(UUID id, User domainObj) {
        var mappedEntity = mapper
                .toEntity(getById(id));

        mappedEntity.setId(id);
        mappedEntity.setCpf(domainObj.cpf());
        mappedEntity.setFullName(domainObj.fullName());
        mappedEntity.setUsername(domainObj.username());
        mappedEntity.setPassword(domainObj.password());

        return mapper.toDomainObj(repository
                .save(mappedEntity));
    }

    public void deleteById(UUID id) {
        var mappedEntity = mapper
                .toEntity(getById(id));

        mappedEntity.setId(id);
        repository.deleteById(mappedEntity.getId());
    }
}