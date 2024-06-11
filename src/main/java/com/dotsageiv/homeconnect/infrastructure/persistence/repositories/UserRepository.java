package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {}