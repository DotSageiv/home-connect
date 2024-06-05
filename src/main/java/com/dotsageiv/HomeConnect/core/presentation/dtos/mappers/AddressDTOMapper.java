package com.dotsageiv.HomeConnect.core.presentation.dtos.mappers;

import com.dotsageiv.HomeConnect.core.domain.entities.Address;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.AddressRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.AddressResponse;

public class AddressDTOMapper {
    public Address toDomainObj(AddressRequest request) {
        return new Address(request.city(), request.state());
    }

    public AddressRequest toRequest(Address domainObj) {
        return new AddressRequest(domainObj.city(), domainObj.state());
    }

    public AddressResponse toResponse(Address domainObj) {
        return new AddressResponse(domainObj.city(), domainObj.state());
    }
}