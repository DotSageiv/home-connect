package com.dotsageiv.HomeConnect.infrastructure.persistence.repositories;

import com.dotsageiv.HomeConnect.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {}