package com.dotsageiv.homeconnect.core.domain.interfaces;

import com.dotsageiv.homeconnect.core.domain.entities.User;

public interface UserService {
    User create(User domainObj);

    User getById(Long userId);

    Iterable<User> getAll();

    User updateById(Long userId, User domainObj);

    void deleteById(Long userId);
}