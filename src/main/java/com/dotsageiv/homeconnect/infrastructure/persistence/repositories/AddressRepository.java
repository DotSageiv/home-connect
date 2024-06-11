package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
    List<AddressEntity> findByUserEntityId(UUID userId);
}