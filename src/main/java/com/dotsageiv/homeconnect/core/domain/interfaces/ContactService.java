package com.dotsageiv.homeconnect.core.domain.interfaces;

import java.util.List;
import java.util.UUID;

import com.dotsageiv.homeconnect.core.domain.entities.Contact;

public interface ContactService {
    Contact create(UUID userId, Contact domainObj);

    Contact getById(UUID contactId, UUID userId);

    List<Contact> getAll(UUID userId);

    Contact updateById(UUID contactId, UUID userId, Contact domainObj);

    void deleteById(UUID contactId, UUID userId);
}