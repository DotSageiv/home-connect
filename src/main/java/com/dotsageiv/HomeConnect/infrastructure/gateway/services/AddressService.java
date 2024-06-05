package com.dotsageiv.HomeConnect.infrastructure.gateway.services;

import com.dotsageiv.HomeConnect.core.domain.entities.Address;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.AddressMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.AddressRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class AddressService {
    private final UserMapper userMapper;
    private final UserService userService;

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressService(UserMapper userMapper,
                          UserService userService,
                          AddressMapper addressMapper,
                          AddressRepository addressRepository) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    public Address create(UUID userId, Address domainObj) {
        var mappedAddressEntity = addressMapper
                .toEntity(domainObj);

        var mappedUserEntity = userMapper
                .toEntity(userService.getById(userId));

        mappedUserEntity.setId(userId);
        mappedAddressEntity.setUserEntity(mappedUserEntity);

        return addressMapper.toDomainObj(addressRepository
                .save(mappedAddressEntity));
    }

    public List<Address> getAll(UUID userId) {
        var addressEntities = addressRepository
                .findByUserEntityId(userId)
                .spliterator();

        return StreamSupport.stream(addressEntities, false)
                .map(addressMapper::toDomainObj)
                .toList();
    }

    public Address getById(UUID userId, UUID addressId) {
        var existAddressEntity = addressRepository
                .findById(addressId)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Endereço não existe!"));

        var mappedUserEntity = userMapper
                .toEntity(userService.getById(userId));

        mappedUserEntity.setId(userId);
        mappedUserEntity.getAddresses().add(existAddressEntity);

        return addressMapper.toDomainObj(
                mappedUserEntity.getAddresses().stream()
                        .filter(cId -> cId.getId().equals(existAddressEntity.getId()))
                        .findFirst()
                        .get());
    }

    public Address updateById(UUID userId, UUID addresId, Address domainObj) {
        var mappedAddressEntity = addressMapper
                .toEntity(getById(userId, addresId));

        var mappedUserEntity = userMapper
                .toEntity(userService.getById(userId));

        mappedAddressEntity.setId(addresId);
        mappedUserEntity.setId(userId);

        mappedAddressEntity.setCity(domainObj.city());
        mappedAddressEntity.setState(domainObj.state());

        mappedAddressEntity.setUserEntity(mappedUserEntity);
        mappedUserEntity.getAddresses().add(mappedAddressEntity);

        return addressMapper.toDomainObj(addressRepository
                .save(mappedAddressEntity));
    }

    public void deleteById(UUID userId, UUID addressId) {
        var mappedAddressEntity = addressMapper
                .toEntity(getById(userId, addressId));

        mappedAddressEntity.setId(addressId);
        addressRepository.deleteById(mappedAddressEntity.getId());
    }
}