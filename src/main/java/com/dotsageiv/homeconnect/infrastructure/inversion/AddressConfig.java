package com.dotsageiv.homeconnect.infrastructure.inversion;

import com.dotsageiv.homeconnect.core.presentation.dtos.mappers.AddressDTOMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.AddressMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.homeconnect.infrastructure.gateway.services.AddressServiceManager;
import com.dotsageiv.homeconnect.infrastructure.gateway.services.UserServiceManager;
import com.dotsageiv.homeconnect.infrastructure.persistence.repositories.AddressRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {
    @Bean
    public AddressMapper addressMapper() {
        return new AddressMapper();
    }

    @Bean
    public AddressDTOMapper addressDTOMapper() {
        return new AddressDTOMapper();
    }

    @Bean
    public AddressServiceManager addressServiceManager(UserMapper userMapper,
                                                       UserServiceManager userServiceManager,
                                                       AddressRepository addressRepository,
                                                       AddressMapper addressMapper) {
        return new AddressServiceManager(userMapper,
                userServiceManager,
                addressMapper,
                addressRepository);
    }
}