package com.dotsageiv.homeconnect.core.domain.entities;

import lombok.Builder;

@Builder
public record Contact(String email, String phoneNumber) {}