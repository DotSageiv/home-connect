package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.ContactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends CrudRepository<ContactEntity, UUID> {
    List<ContactEntity> findByUserEntityId(UUID userId);
}