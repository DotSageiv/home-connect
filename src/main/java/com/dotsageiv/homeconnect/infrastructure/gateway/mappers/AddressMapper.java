package com.dotsageiv.homeconnect.infrastructure.gateway.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.Address;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.AddressEntity;

public class AddressMapper {
    public Address toDomainObj(AddressEntity entity) {
        return Address.builder()
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
}