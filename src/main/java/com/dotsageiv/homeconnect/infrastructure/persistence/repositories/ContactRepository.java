package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.ContactEntity;

public interface ContactRepository extends CrudRepository<ContactEntity, UUID> {
    List<ContactEntity> findByUserEntityId(UUID userId);
}