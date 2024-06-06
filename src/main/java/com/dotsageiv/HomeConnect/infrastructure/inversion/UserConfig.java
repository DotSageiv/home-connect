package com.dotsageiv.HomeConnect.infrastructure.inversion;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.UserDTOMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.UserServiceManager;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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