package com.dotsageiv.homeconnect.infrastructure.gateway.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.User;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.UserEntity;

public class UserMapper {
    public User toDomainObj(UserEntity entity) {
        return new User(entity.getCpf(),
                entity.getFullName(),
                entity.getUsername(),
                entity.getPassword());
    }

    public UserEntity toEntity(User domainObj) {
        return new UserEntity(domainObj.cpf(),
                domainObj.fullName(),
                domainObj.username(),
                domainObj.password());
    }
}