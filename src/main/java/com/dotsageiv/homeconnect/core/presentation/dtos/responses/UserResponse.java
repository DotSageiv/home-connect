package com.dotsageiv.homeconnect.core.presentation.dtos.responses;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String cpf,
        String fullName,
        String username) {}