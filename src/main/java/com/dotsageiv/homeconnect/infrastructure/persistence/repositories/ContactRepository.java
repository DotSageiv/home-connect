package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.ContactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<ContactEntity, Long> {
    List<ContactEntity> findByUserEntityId(Long userId);
}