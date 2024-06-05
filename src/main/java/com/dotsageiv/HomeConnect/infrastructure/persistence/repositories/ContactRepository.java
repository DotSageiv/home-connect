package com.dotsageiv.HomeConnect.infrastructure.persistence.repositories;

import com.dotsageiv.HomeConnect.infrastructure.persistence.entities.ContactEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ContactRepository extends CrudRepository<ContactEntity, UUID> {}