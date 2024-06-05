package com.dotsageiv.HomeConnect.infrastructure.gateway.services;

import com.dotsageiv.HomeConnect.core.domain.entities.Contact;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.ContactMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.ContactRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class ContactService {
    private final UserMapper userMapper;
    private final UserService userService;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactService(UserMapper userMapper,
                          UserService userService,
                          ContactMapper contactMapper,
                          ContactRepository contactRepository) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
    }

    public Contact create(UUID userId, Contact domainObj) {
        var mappedContactEntity = contactMapper
                .toEntity(domainObj);

        var mappedUserEntity = userMapper
                .toEntity(userService.getById(userId));

        mappedUserEntity.setId(userId);
        mappedContactEntity.setUserEntity(mappedUserEntity);

        return contactMapper.toDomainObj(contactRepository
                .save(mappedContactEntity));
    }

    public List<Contact> getAll(UUID userId) {
        var contactEntities = contactRepository
                .findByUserEntityId(userId)
                .spliterator();

        return StreamSupport.stream(contactEntities, false)
                .map(contactMapper::toDomainObj)
                .toList();
    }

    public Contact getById(UUID userId, UUID contactId) {
        var existContactEntity = contactRepository
                .findById(contactId)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Contato não existe!"));

        var mappedUserEntity = userMapper
                .toEntity(userService.getById(userId));

        mappedUserEntity.setId(userId);
        mappedUserEntity.getContacts().add(existContactEntity);

        return contactMapper.toDomainObj(
                mappedUserEntity.getContacts().stream()
                        .filter(cId -> cId.getId().equals(existContactEntity.getId()))
                        .findFirst()
                        .get());
    }

    public Contact updateById(UUID userId, UUID contactId, Contact domainObj) {
        var mappedContactEntity = contactMapper
                .toEntity(getById(userId, contactId));

        var mappedUserEntity = userMapper
                .toEntity(userService.getById(userId));

        mappedContactEntity.setId(contactId);
        mappedUserEntity.setId(userId);

        mappedContactEntity.setEmail(domainObj.email());
        mappedContactEntity.setPhoneNumber(domainObj.phoneNumber());

        mappedContactEntity.setUserEntity(mappedUserEntity);
        mappedUserEntity.getContacts().add(mappedContactEntity);

        return contactMapper.toDomainObj(contactRepository
                .save(mappedContactEntity));
    }

    public void deleteById(UUID userId, UUID contactId) {
        var mappedContactEntity = contactMapper
                .toEntity(getById(userId, contactId));

        mappedContactEntity.setId(contactId);
        contactRepository.deleteById(mappedContactEntity.getId());
    }
}