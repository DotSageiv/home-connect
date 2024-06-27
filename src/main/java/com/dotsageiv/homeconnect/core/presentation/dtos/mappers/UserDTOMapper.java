package com.dotsageiv.homeconnect.core.presentation.dtos.mappers;

import com.dotsageiv.homeconnect.core.domain.entities.User;
import com.dotsageiv.homeconnect.core.presentation.dtos.requests.UserRequest;
import com.dotsageiv.homeconnect.core.presentation.dtos.responses.UserResponse;

public class UserDTOMapper {
    public User toDomainObj(UserRequest request) {
        return User.builder()
                .cpf(request.cpf())
                .fullName(request.fullName())
                .username(request.username())
                .build();
    }

    public UserRequest toRequest(User domainObj) {
        return UserRequest.builder()
                .cpf(domainObj.cpf())
                .fullName(domainObj.fullName())
                .username(domainObj.username())
                .build();
    }

    public UserResponse toResponse(User domainObj) {
        return UserResponse.builder()
                .id(domainObj.id())
                .cpf(domainObj.cpf())
                .fullName(domainObj.fullName())
                .username(domainObj.username())
                .build();
    }
}