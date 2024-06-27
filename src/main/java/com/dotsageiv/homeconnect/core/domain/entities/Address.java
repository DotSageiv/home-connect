package com.dotsageiv.homeconnect.core.domain.entities;

import lombok.Builder;

@Builder
public record Address(Long id, String city, String state) {}