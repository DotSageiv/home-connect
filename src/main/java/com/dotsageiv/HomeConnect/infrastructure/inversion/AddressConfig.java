package com.dotsageiv.HomeConnect.infrastructure.inversion;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.AddressDTOMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.AddressMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.AddressServiceManager;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.UserServiceManager;
import com.dotsageiv.HomeConnect.infrastructure.persistence.repositories.AddressRepository;
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