package com.dotsageiv.homeconnect.core.domain.interfaces;

import java.util.UUID;

import com.dotsageiv.homeconnect.core.domain.entities.User;

public interface UserService {
    User create(User domainObj);

    User getById(UUID userId);

    Iterable<User> getAll();

    User updateById(UUID userId, User domainObj);

    void deleteById(UUID userId);
}