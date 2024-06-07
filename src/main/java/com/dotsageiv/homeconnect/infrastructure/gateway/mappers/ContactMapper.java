package com.dotsageiv.homeconnect.infrastructure.gateway.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.Contact;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.ContactEntity;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.UserEntity;

public class ContactMapper {
    public Contact toDomainObj(ContactEntity entity) {
        return Contact.builder()
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    public ContactEntity toEntity(Contact domainObj) {
        return ContactEntity.builder()
                .email(domainObj.email())
                .phoneNumber(domainObj.phoneNumber())
                .build();
    }

    public ContactEntity toEntity(Contact domainObj, UserEntity entity) {
        return ContactEntity.builder()
                .email(domainObj.email())
                .phoneNumber(domainObj.phoneNumber())
                .userEntity(entity)
                .build();
    }
}