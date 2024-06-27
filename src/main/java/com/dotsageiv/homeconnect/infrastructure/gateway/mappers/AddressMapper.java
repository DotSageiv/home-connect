package com.dotsageiv.homeconnect.infrastructure.gateway.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.Address;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.AddressEntity;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.UserEntity;

public class AddressMapper {
    public Address toDomainObj(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .state(entity.getState())
                .build();
    }

    public AddressEntity toEntity(Address domainObj) {
        return AddressEntity.builder()
                .city(domainObj.city())
                .state(domainObj.state())
                .build();
    }

    public AddressEntity toEntity(Address domainObj, UserEntity entity) {
        return AddressEntity.builder()
                .id(domainObj.id())
                .city(domainObj.city())
                .state(domainObj.state())
                .userEntity(entity)
                .build();
    }
}