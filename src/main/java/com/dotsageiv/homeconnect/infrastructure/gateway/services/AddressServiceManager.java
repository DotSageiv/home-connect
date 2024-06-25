package com.dotsageiv.homeconnect.infrastructure.gateway.services;

import com.dotsageiv.homeconnect.core.domain.entities.Address;
import com.dotsageiv.homeconnect.core.domain.interfaces.AddressService;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.AddressMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.homeconnect.infrastructure.persistence.notifications.EntityNotFoundNotification;
import com.dotsageiv.homeconnect.infrastructure.persistence.repositories.AddressRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
public class AddressServiceManager implements AddressService {
    private final UserMapper userMapper;
    private final UserServiceManager userServiceManager;

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public Address create(Long userId, Address domainObj) {
        var mappedUserEntity = userMapper
                .toEntity(userId, userServiceManager.getById(userId));

        var mappedAddressEntity = addressMapper
                .toEntity(domainObj, mappedUserEntity);

        mappedUserEntity.getAddresses().add(mappedAddressEntity);

        return addressMapper.toDomainObj(addressRepository
                .save(mappedAddressEntity));
    }

    @Override
    public List<Address> getAll(Long userId) {
        var addressEntities = addressRepository
                .findByUserEntityId(userId)
                .spliterator();

        return StreamSupport.stream(addressEntities, false)
                .map(addressMapper::toDomainObj)
                .toList();
    }

    @Override
    public Address getById(Long addressId, Long userId) {
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
    public Address updateById(Long addressId, Long userId, Address domainObj) {
        var mappedUserEntity = userMapper
                .toEntity(userId, userServiceManager.getById(userId));

        var mappedAddressEntity = addressMapper
                .toEntity(getById(addressId, userId), mappedUserEntity);

        mappedAddressEntity.setId(addressId);
        mappedAddressEntity.setCity(domainObj.city());
        mappedAddressEntity.setState(domainObj.state());

        mappedUserEntity.getAddresses().add(mappedAddressEntity);

        return addressMapper.toDomainObj(addressRepository
                .save(mappedAddressEntity));
    }

    @Override
    public void deleteById(Long addressId, Long userId) {
        var mappedAddressEntity = addressMapper
                .toEntity(getById(addressId, userId));

        mappedAddressEntity.setId(addressId);
        addressRepository.deleteById(mappedAddressEntity.getId());
    }
}