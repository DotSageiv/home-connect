package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
    List<AddressEntity> findByUserEntityId(UUID userId);
}