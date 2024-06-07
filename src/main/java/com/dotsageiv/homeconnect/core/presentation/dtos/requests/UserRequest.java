package com.dotsageiv.homeconnect.core.presentation.dtos.requests;

import lombok.Builder;

@Builder
public record UserRequest(String cpf, String fullName,
                          String username, String password) {}