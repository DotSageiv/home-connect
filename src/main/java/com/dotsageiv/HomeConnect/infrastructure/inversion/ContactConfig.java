package com.dotsageiv.HomeConnect.infrastructure.inversion;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.ContactDTOMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.ContactMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.ContactService;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.UserService;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.ContactRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfig {
    @Bean
    public ContactMapper contactMapper() {
        return new ContactMapper();
    }

    @Bean
    public ContactDTOMapper contactDTOMapper() {
        return new ContactDTOMapper();
    }

    @Bean
    public ContactService contactService(UserMapper userMapper,
                                         UserService userService,
                                         ContactRepository contactRepository,
                                         ContactMapper contactMapper) {
        return new ContactService(userMapper, userService, contactMapper, contactRepository);
    }
}