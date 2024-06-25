package com.dotsageiv.homeconnect.core.domain.interfaces;

import com.dotsageiv.homeconnect.core.domain.entities.Address;

import java.util.List;

public interface AddressService {
    Address create(Long userId, Address domainObj);

    Address getById(Long addressId, Long userId);

    List<Address> getAll(Long userId);

    Address updateById(Long adressId, Long userId, Address domainObj);

    void deleteById(Long addressId, Long userId);
}