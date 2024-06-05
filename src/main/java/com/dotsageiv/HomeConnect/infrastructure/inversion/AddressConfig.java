package com.dotsageiv.HomeConnect.infrastructure.inversion;

import com.dotsageiv.HomeConnect.core.presentation.dtos.mappers.AddressDTOMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.AddressMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.mappers.UserMapper;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.AddressService;
import com.dotsageiv.HomeConnect.infrastructure.gateway.services.UserService;
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
    public AddressService addressService(UserMapper userMapper,
                                         UserService userService,
                                         AddressRepository addressRepository,
                                         AddressMapper addressMapper) {
        return new AddressService(userMapper, userService, addressMapper, addressRepository);
    }
}