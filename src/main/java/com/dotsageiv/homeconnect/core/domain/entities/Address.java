package com.dotsageiv.homeconnect.core.domain.entities;

import lombok.Builder;

@Builder
public record Address(String city, String state) {}