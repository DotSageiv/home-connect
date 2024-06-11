package com.dotsageiv.homeconnect.infrastructure.inversion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dotsageiv.homeconnect.core.presentation.dtos.mappers.UserDTOMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.services.UserServiceManager;
import com.dotsageiv.homeconnect.infrastructure.persistence.repositories.UserRepository;

@Configuration
public class UserConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }

    @Bean
    public UserServiceManager userServiceManager(UserRepository repository, UserMapper mapper) {
        return new UserServiceManager(mapper, repository);
    }
}