package com.dotsageiv.homeconnect.infrastructure.gateway.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.User;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.UserEntity;

public class UserMapper {
    public User toDomainObj(UserEntity entity) {
        return User.builder()
                .cpf(entity.getCpf())
                .fullName(entity.getFullName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public UserEntity toEntity(User domainObj) {
        return UserEntity.builder()
                .cpf(domainObj.cpf())
                .fullName(domainObj.fullName())
                .username(domainObj.username())
                .password(domainObj.password())
                .build();
    }
}