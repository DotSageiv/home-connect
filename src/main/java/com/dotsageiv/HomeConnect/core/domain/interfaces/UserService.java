package com.dotsageiv.HomeConnect.core.domain.interfaces;

import com.dotsageiv.HomeConnect.core.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User create(User domainObj);

    User getById(UUID userId);

    Iterable<User> getAll();

    User updateById(UUID userId, User domainObj);

    void deleteById(UUID userId);
}