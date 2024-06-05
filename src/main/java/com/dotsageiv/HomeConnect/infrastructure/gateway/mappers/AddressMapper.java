package com.dotsageiv.HomeConnect.infrastructure.gateway.mappers;

import com.dotsageiv.HomeConnect.core.domain.entities.Address;
import com.dotsageiv.HomeConnect.infrastructure.persistence.entities.AddressEntity;

public class AddressMapper {
    public Address toDomainObj(AddressEntity entity) {
        return new Address(entity.getCity(), entity.getState());
    }

    public AddressEntity toEntity(Address domainObj) {
        return new AddressEntity(domainObj.city(), domainObj.state());
    }
}