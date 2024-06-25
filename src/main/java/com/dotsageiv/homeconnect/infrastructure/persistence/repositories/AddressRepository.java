package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findByUserEntityId(Long userId);
}