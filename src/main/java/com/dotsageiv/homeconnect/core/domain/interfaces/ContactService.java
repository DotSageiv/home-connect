package com.dotsageiv.homeconnect.core.domain.interfaces;

import com.dotsageiv.homeconnect.core.domain.entities.Contact;

import java.util.List;

public interface ContactService {
    Contact create(Long userId, Contact domainObj);

    Contact getById(Long contactId, Long userId);

    List<Contact> getAll(Long userId);

    Contact updateById(Long contactId, Long userId, Contact domainObj);

    void deleteById(Long contactId, Long userId);
}