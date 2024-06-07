package com.dotsageiv.homeconnect.core.presentation.dtos.requests;

import lombok.Builder;

@Builder
public record ContactRequest(String email, String phoneNumber) {}