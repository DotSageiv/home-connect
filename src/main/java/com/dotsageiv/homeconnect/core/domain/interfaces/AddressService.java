package com.dotsageiv.homeconnect.core.domain.interfaces;

import java.util.List;
import java.util.UUID;

import com.dotsageiv.homeconnect.core.domain.entities.Address;

public interface AddressService {
    Address create(UUID userId, Address domainObj);

    Address getById(UUID addressId, UUID userId);

    List<Address> getAll(UUID userId);

    Address updateById(UUID adressId, UUID userId, Address domainObj);

    void deleteById(UUID addressId, UUID userId);
}