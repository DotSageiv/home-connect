package com.dotsageiv.homeconnect.infrastructure.gateway.services;

import com.dotsageiv.homeconnect.core.domain.entities.Contact;
import com.dotsageiv.homeconnect.core.domain.interfaces.ContactService;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.ContactMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.homeconnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.homeconnect.infrastructure.persistence.repositories.ContactRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class ContactServiceManager implements ContactService {
    private final UserMapper userMapper;
    private final UserServiceManager userServiceManager;

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactServiceManager(UserMapper userMapper,
                                 UserServiceManager userServiceManager,
                                 ContactMapper contactMapper,
                                 ContactRepository contactRepository) {
        this.userMapper = userMapper;
        this.userServiceManager = userServiceManager;
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(UUID userId, Contact domainObj) {
        var mappedContactEntity = contactMapper
                .toEntity(domainObj);

        var mappedUserEntity = userMapper
                .toEntity(userServiceManager.getById(userId));

        mappedUserEntity.setId(userId);
        mappedContactEntity.setUserEntity(mappedUserEntity);

        return contactMapper.toDomainObj(contactRepository
                .save(mappedContactEntity));
    }

    @Override
    public List<Contact> getAll(UUID userId) {
        var contactEntities = contactRepository
                .findByUserEntityId(userId)
                .spliterator();

        return StreamSupport.stream(contactEntities, false)
                .map(contactMapper::toDomainObj)
                .toList();
    }

    @Override
    public Contact getById(UUID contactId, UUID userId) {
        var existContactEntity = contactRepository
                .findById(contactId)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Contato não existe!"));

        var mappedUserEntity = userMapper
                .toEntity(userServiceManager.getById(userId));

        mappedUserEntity.setId(userId);
        mappedUserEntity.getContacts().add(existContactEntity);

        return contactMapper.toDomainObj(
                mappedUserEntity.getContacts().stream()
                        .filter(cId -> cId.getId().equals(existContactEntity.getId()))
                        .findFirst()
                        .get());
    }

    @Override
    public Contact updateById(UUID contactId, UUID userId, Contact domainObj) {
        var mappedContactEntity = contactMapper
                .toEntity(getById(contactId, userId));

        var mappedUserEntity = userMapper
                .toEntity(userServiceManager.getById(userId));

        mappedContactEntity.setId(contactId);
        mappedUserEntity.setId(userId);

        mappedContactEntity.setEmail(domainObj.email());
        mappedContactEntity.setPhoneNumber(domainObj.phoneNumber());

        mappedContactEntity.setUserEntity(mappedUserEntity);
        mappedUserEntity.getContacts().add(mappedContactEntity);

        return contactMapper.toDomainObj(contactRepository
                .save(mappedContactEntity));
    }

    @Override
    public void deleteById(UUID contactId, UUID userId) {
        var mappedContactEntity = contactMapper
                .toEntity(getById(contactId, userId));

        mappedContactEntity.setId(contactId);
        contactRepository.deleteById(mappedContactEntity.getId());
    }
}