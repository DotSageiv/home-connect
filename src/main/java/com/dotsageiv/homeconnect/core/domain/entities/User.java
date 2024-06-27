package com.dotsageiv.homeconnect.core.domain.entities;

import lombok.Builder;

@Builder
public record User(Long id, String cpf, String fullName,
                   String username, String password) {}