package com.dotsageiv.homeconnect.core.domain.entities;

import lombok.Builder;

@Builder
public record Contact(Long id, String email, String phoneNumber) {}