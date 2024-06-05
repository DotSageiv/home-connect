package com.dotsageiv.HomeConnect.infrastructure.gateway.services;

import com.dotsageiv.HomeConnect.core.domain.entities.Contact;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.ContactMapper;
import com.dotsageiv.HomeConnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.ContactRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class ContactService {
    private final ContactMapper mapper;
    private final ContactRepository repository;

    public ContactService(ContactMapper mapper, ContactRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Contact create(Contact domainObj) {
        var mappedEntity = mapper
                .toEntity(domainObj);

        return mapper.toDomainObj(repository
                .save(mappedEntity));
    }

    public List<Contact> getAll() {
        var entities = repository
                .findAll()
                .spliterator();

        return StreamSupport.stream(entities, false)
                .map(mapper::toDomainObj)
                .toList();
    }

    public Contact getById(UUID id) {
        var existEntity = repository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Usuário não existe!"));

        return mapper.toDomainObj(existEntity);
    }

    public Contact updateById(UUID id, Contact domainObj) {
        var mappedEntity = mapper
                .toEntity(getById(id));

        mappedEntity.setId(id);
        mappedEntity.setEmail(domainObj.email());
        mappedEntity.setPhoneNumber(domainObj.phoneNumber());

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