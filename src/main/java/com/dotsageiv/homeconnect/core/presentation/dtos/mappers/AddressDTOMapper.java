package com.dotsageiv.homeconnect.core.presentation.dtos.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.Address;
import com.dotsageiv.homeconnect.core.presentation.dtos.requests.AddressRequest;
import com.dotsageiv.homeconnect.core.presentation.dtos.responses.AddressResponse;

public class AddressDTOMapper {
    public Address toDomainObj(AddressRequest request) {
        return Address.builder()
                .city(request.city())
                .state(request.state())
                .build();
    }

    public AddressRequest toRequest(Address domainObj) {
        return AddressRequest.builder()
                .city(domainObj.city())
                .state(domainObj.state())
                .build();
    }

    public AddressResponse toResponse(Long userId, Address domainObj) {
        return AddressResponse.builder()
                .addressId(domainObj.id())
                .userId(userId)
                .city(domainObj.city())
                .state(domainObj.state())
                .build();
    }

    public AddressResponse toResponse(Address domainObj) {
        return AddressResponse.builder()
                .city(domainObj.city())
                .state(domainObj.state())
                .build();
    }
}