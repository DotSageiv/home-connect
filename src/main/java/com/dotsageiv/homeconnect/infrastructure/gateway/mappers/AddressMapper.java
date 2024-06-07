package com.dotsageiv.homeconnect.infrastructure.gateway.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.Address;
import com.dotsageiv.homeconnect.infrastructure.persistence.entities.AddressEntity;

public class AddressMapper {
    public Address toDomainObj(AddressEntity entity) {
        return new Address(entity.getCity(), entity.getState());
    }

    public AddressEntity toEntity(Address domainObj) {
        return new AddressEntity(domainObj.city(), domainObj.state());
    }
}