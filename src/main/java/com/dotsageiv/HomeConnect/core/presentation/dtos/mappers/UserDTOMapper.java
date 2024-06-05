package com.dotsageiv.HomeConnect.core.presentation.dtos.mappers;

import com.dotsageiv.HomeConnect.core.domain.entities.User;
import com.dotsageiv.HomeConnect.core.presentation.dtos.requests.UserRequest;
import com.dotsageiv.HomeConnect.core.presentation.dtos.responses.UserResponse;

public class UserDTOMapper {
    public User toDomainObj(UserRequest request) {
        return new User(request.cpf(),
                request.fullName(),
                request.username(),
                request.password());
    }

    public UserRequest toRequest(User domainObj) {
        return new UserRequest(domainObj.cpf(),
                domainObj.fullName(),
                domainObj.username(),
                domainObj.password());
    }

    public UserResponse toResponse(User domainObj) {
        return new UserResponse(domainObj.cpf(),
                domainObj.fullName(),
                domainObj.username());
    }
}