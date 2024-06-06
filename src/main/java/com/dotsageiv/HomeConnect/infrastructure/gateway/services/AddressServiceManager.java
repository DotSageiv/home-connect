package com.dotsageiv.HomeConnect.infrastructure.gateway.services;

import com.dotsageiv.HomeConnect.core.domain.entities.Address;
import com.dotsageiv.HomeConnect.core.domain.interfaces.AddressService;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.AddressMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.AddressRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class AddressServiceManager implements AddressService {
    private final UserMapper userMapper;
    private final UserServiceManager userServiceManager;

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressServiceManager(UserMapper userMapper,
                                 UserServiceManager userServiceManager,
                                 AddressMapper addressMapper,
                                 AddressRepository addressRepository) {
        this.userMapper = userMapper;
        this.userServiceManager = userServiceManager;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(UUID userId, Address domainObj) {
        var mappedAddressEntity = addressMapper
                .toEntity(domainObj);

        var mappedUserEntity = userMapper
                .toEntity(userServiceManager.getById(userId));

        mappedUserEntity.setId(userId);
        mappedAddressEntity.setUserEntity(mappedUserEntity);

        return addressMapper.toDomainObj(addressRepository
                .save(mappedAddressEntity));
    }

    @Override
    public List<Address> getAll(UUID userId) {
        var addressEntities = addressRepository
                .findByUserEntityId(userId)
                .spliterator();

        return StreamSupport.stream(addressEntities, false)
                .map(addressMapper::toDomainObj)
                .toList();
    }

    @Override
    public Address getById(UUID addressId, UUID userId) {
        var existAddressEntity = addressRepository
                .findById(addressId)
                .orElseThrow(() ->
                        new EntityNotFoundNotification("Endereço não existe!"));

        var mappedUserEntity = userMapper
                .toEntity(userServiceManager.getById(userId));

        mappedUserEntity.setId(userId);
        mappedUserEntity.getAddresses().add(existAddressEntity);

        return addressMapper.toDomainObj(
                mappedUserEntity.getAddresses().stream()
                        .filter(cId -> cId.getId().equals(existAddressEntity.getId()))
                        .findFirst()
                        .get());
    }

    @Override
    public Address updateById(UUID addressId, UUID userId, Address domainObj) {
        var mappedAddressEntity = addressMapper
                .toEntity(getById(addressId, userId));

        var mappedUserEntity = userMapper
                .toEntity(userServiceManager.getById(userId));

        mappedAddressEntity.setId(addressId);
        mappedUserEntity.setId(userId);

        mappedAddressEntity.setCity(domainObj.city());
        mappedAddressEntity.setState(domainObj.state());

        mappedAddressEntity.setUserEntity(mappedUserEntity);
        mappedUserEntity.getAddresses().add(mappedAddressEntity);

        return addressMapper.toDomainObj(addressRepository
                .save(mappedAddressEntity));
    }

    @Override
    public void deleteById(UUID addressId, UUID userId) {
        var mappedAddressEntity = addressMapper
                .toEntity(getById(addressId, userId));

        mappedAddressEntity.setId(addressId);
        addressRepository.deleteById(mappedAddressEntity.getId());
    }
}