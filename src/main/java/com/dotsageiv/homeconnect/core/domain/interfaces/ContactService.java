package com.dotsageiv.homeconnect.core.domain.interfaces;

import com.dotsageiv.homeconnect.core.domain.entities.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    Contact create(UUID userId, Contact domainObj);

    Contact getById(UUID contactId, UUID userId);

    List<Contact> getAll(UUID userId);

    Contact updateById(UUID contactId, UUID userId, Contact domainObj);

    void deleteById(UUID contactId, UUID userId);
}