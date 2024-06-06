package com.dotsageiv.HomeConnect.core.domain.interfaces;

import com.dotsageiv.HomeConnect.core.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address create(UUID userId, Address domainObj);

    Address getById(UUID addressId, UUID userId);

    List<Address> getAll(UUID userId);

    Address updateById(UUID adressId, UUID userId, Address domainObj);

    void deleteById(UUID addressId, UUID userId);
}