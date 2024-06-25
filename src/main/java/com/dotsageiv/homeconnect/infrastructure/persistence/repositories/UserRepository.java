package com.dotsageiv.homeconnect.infrastructure.persistence.repositories;

import com.dotsageiv.homeconnect.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {}