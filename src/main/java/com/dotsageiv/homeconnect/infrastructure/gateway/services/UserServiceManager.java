package com.dotsageiv.homeconnect.infrastructure.gateway.services;

import com.dotsageiv.homeconnect.core.domain.entities.User;
import com.dotsageiv.homeconnect.core.domain.interfaces.UserService;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.homeconnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.homeconnect.infrastructure.persistence.repositories.UserRepository;

import java.util.UUID;
import java.util.stream.StreamSupport;

public class UserServiceManager implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    public UserServiceManager(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public User create(User domainObj) {
        var mappedEntity = mapper
                .toEntity(domainObj);

        return mapper.toDomainObj(repository
                .save(mappedEntity));
    }

    @Override
    public Iterable<User> getAll() {
        var entities = repository
                .findAll()
                .spliterator();

        return StreamSupport.stream(entities, false)
                .map(mapper::toDomainObj)
                .toList();
    }

    @Override
    public User getById(UUID userId) {
        var existEntity = repository
                .findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Usuário não existe!"));

        return mapper.toDomainObj(existEntity);
    }

    @Override
    public User updateById(UUID userId, User domainObj) {
        var mappedEntity = mapper
                .toEntity(getById(userId));

        mappedEntity.setId(userId);
        mappedEntity.setCpf(domainObj.cpf());
        mappedEntity.setFullName(domainObj.fullName());
        mappedEntity.setUsername(domainObj.username());
        mappedEntity.setPassword(domainObj.password());

        return mapper.toDomainObj(repository
                .save(mappedEntity));
    }

    @Override
    public void deleteById(UUID userId) {
        var mappedEntity = mapper
                .toEntity(getById(userId));

        mappedEntity.setId(userId);
        repository.deleteById(mappedEntity.getId());
    }
}