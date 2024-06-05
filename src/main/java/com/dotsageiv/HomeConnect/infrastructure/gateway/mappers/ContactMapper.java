package com.dotsageiv.HomeConnect.infrastructure.gateway.mappers;

import com.dotsageiv.HomeConnect.core.domain.entities.Contact;
import com.dotsageiv.HomeConnect.infrastructure.persistence.entities.ContactEntity;

public class ContactMapper {
    public Contact toDomainObj(ContactEntity entity) {
        return new Contact(entity.getEmail(), entity.getPhoneNumber());
    }

    public ContactEntity toEntity(Contact domainObj) {
        return new ContactEntity(domainObj.email(), domainObj.phoneNumber());
    }
}